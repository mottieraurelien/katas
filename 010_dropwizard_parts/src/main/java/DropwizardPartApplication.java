import auth.DropwizardPartAuthenticator;
import auth.DropwizardPartAuthorizer;
import auth.User;
import config.DropwizardPartConfiguration;
import health.DropwizardPartApplicationHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import resource.PartsResource;
import service.PartsService;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DropwizardPartApplication extends Application<DropwizardPartConfiguration> {

    private static final String H2 = "h2";
    private static final String BEARER = "Bearer";
    private static final String TABLE = "CREATE TABLE PARTS (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(64), CODE VARCHAR(64))";
    private static final String DROPWIZARD_BLOG_SERVICE = "Dropwizard blog service";

    public static void main(String[] args) throws Exception {
        new DropwizardPartApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardPartConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(DropwizardPartConfiguration configuration, Environment environment) {

        // Datasource configuration
        final DataSource dataSource =
                configuration.getDataSourceFactory().build(environment.metrics(), H2);
        DBI dbi = new DBI(dataSource);

        // Creation of the table PARTS :
        try {
            PreparedStatement stmt = dataSource.getConnection().prepareStatement(TABLE);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Can't create the in-memory table PARTS.");
        }

        // Register Health Check
        DropwizardPartApplicationHealthCheck healthCheck = new DropwizardPartApplicationHealthCheck(dbi.onDemand(PartsService.class));
        environment.healthChecks().register(DROPWIZARD_BLOG_SERVICE, healthCheck);

        // Register OAuth authentication
        environment.jersey()
                .register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new DropwizardPartAuthenticator())
                        .setAuthorizer(new DropwizardPartAuthorizer()).setPrefix(BEARER).buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        // Register resources
        environment.jersey().register(new PartsResource(dbi.onDemand(PartsService.class)));

    }

}
