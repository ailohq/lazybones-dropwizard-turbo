package ${packageName};

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.netflix.governator.guice.LifecycleInjector;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.governator.guice.lazy.LazySingleton;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;

import java.time.Clock;
import javax.ws.rs.client.Client;


public class ${applicationName}Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(SessionFactory.class).toProvider(hibernateBundle::getSessionFactory);
        bind(Clock.class).toInstance(Clock.systemDefaultZone());
    }

    @Provides
    @LazySingleton
    public final Client provideClient (Environment environment, ${applicationName}Configuration configuration) {
        return new JerseyClientBuilder(environment)
                .using(configuration.getClient())
                .build("test client");
    }

    public final GuiceBundle<${applicationName}Configuration> getGuiceBundle() {
        return guiceGovernatorBundle;
    }

    public final HibernateBundle<${applicationName}Configuration> getHibernateBundle() {
        return hibernateBundle;
    }

    public final MigrationsBundle<${applicationName}Configuration> getMigrationBundle() {
        return migrationsBundle;
    }

    private final HibernateBundle<${applicationName}Configuration> hibernateBundle =
        new ScanningHibernateBundle<${applicationName}Configuration>("${packageName}.model") {
            @Override
            public DataSourceFactory getDataSourceFactory(${applicationName}Configuration configuration) {
                return configuration.getDataSource();
            }
        };

    private final MigrationsBundle<${applicationName}Configuration> migrationsBundle =
        new MigrationsBundle<${applicationName}Configuration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(${applicationName}Configuration configuration) {
                return configuration.getDataSource();
            }
        };

    private final GuiceBundle<${applicationName}Configuration> guiceGovernatorBundle = GuiceBundle.<${applicationName}Configuration>newBuilder()
        .addModule(this)
        .enableAutoConfig("${packageName}","trunk.dropwizard.newrelic")
        .setConfigClass(${applicationName}Configuration.class)
        .setInjectorFactory((stage, modules) -> LifecycleInjector.builder()
            .inStage(stage)
            .withModules(modules)
            .build()
            .createInjector())
        .build();

}
