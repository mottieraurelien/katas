import config.BlogConfiguration;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.IndexResource;

public class BlogApplication extends Application<BlogConfiguration> {

    public static void main(String[] args) throws Exception {
        new BlogApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(BlogConfiguration blogConfiguration, Environment environment) throws Exception {

        // Register resources :
        environment.jersey().register(new IndexResource());

    }

}
