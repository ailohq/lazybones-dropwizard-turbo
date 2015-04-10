package ${packageName};

import com.fasterxml.jackson.databind.SerializationFeature;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.reader.ClassReaders;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ${applicationName}Application extends Application<${applicationName}Configuration> {

    public static void main(String[] args) throws Exception {
        new ${applicationName}Application().run(args);
    }

    @Override
    public void initialize(final Bootstrap<${applicationName}Configuration> bootstrap) {
        final ${applicationName}Module guiceModule = new ${applicationName}Module();
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new TemplateConfigBundle());
        bootstrap.addBundle(guiceModule.getMigrationBundle());
        bootstrap.addBundle(guiceModule.getHibernateBundle());
        bootstrap.addBundle(guiceModule.getGuiceBundle());
        bootstrap.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(${applicationName}Configuration configuration, Environment environment) throws Exception {
        setUpSwagger(environment);
    }

    private void setUpSwagger(final Environment environment) {
        // Swagger Resource
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());

        // Set the swagger config options
        ApiInfo apiInfo= new ApiInfo(this.getName(), "description of service", null, "contact me", null, null );
        ConfigFactory.config().setApiInfo(apiInfo);
    }


}
