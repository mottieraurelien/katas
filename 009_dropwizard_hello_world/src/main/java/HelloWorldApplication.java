import com.codahale.metrics.health.HealthCheck;
import config.HelloWorldConfiguration;
import health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private static final String HELLO_WORLD_SERVICE = "Hello world service";

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {

        // Register Health Check
        final HealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register(HELLO_WORLD_SERVICE, healthCheck);

        // Register resources
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

    }

}