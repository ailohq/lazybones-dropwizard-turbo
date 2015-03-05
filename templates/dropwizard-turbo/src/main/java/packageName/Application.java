package ${packageName};

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.netflix.governator.guice.LifecycleInjector;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ${applicationName} extends Application<${applicationName}Configuration> {

    public static void main(String[] args) throws Exception {
        new ${applicationName}().run(args);
    }

    @Override
    public void initialize(final Bootstrap<${applicationName}Configuration> bootstrap) {

        bootstrap.addBundle(new TemplateConfigBundle());
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(guiceGovernatorBundle);
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
    }

    final MigrationsBundle<${applicationName}Configuration> migrationsBundle =
            new MigrationsBundle<${applicationName}Configuration>() {
                @Override
                public DataSourceFactory getDataSourceFactory(${applicationName}Configuration configuration) {
                    return configuration.getDataSource();
                }
            };

    final HibernateBundle<${applicationName}Configuration> hibernateBundle =
            new ScanningHibernateBundle<${applicationName}Configuration>("${packageName}.model") {
                @Override
                public DataSourceFactory getDataSourceFactory(${applicationName}Configuration configuration) {
                    return configuration.getDataSource();
                }
            };

    final GuiceBundle<${applicationName}Configuration> guiceGovernatorBundle = GuiceBundle.<${applicationName}Configuration>newBuilder()
            .addModule(new ${applicationName}Module(hibernateBundle))
            .enableAutoConfig(
                    "${packageName}.resources",
                    "${packageName}.health",
                    "trunk.dropwizard.newrelic"
            )
            .setConfigClass(${applicationName}Configuration.class)
            .setInjectorFactory((stage, modules) -> LifecycleInjector.builder()
                    .inStage(stage)
                    .withModules(modules)
                    .build()
                    .createInjector())
            .build();

}
