package ${packageName};

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.governator.guice.lazy.LazySingleton;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

import javax.ws.rs.client.Client;

public class ${applicationName}Module extends AbstractModule {

    private final HibernateBundle hibernateBundle;

    public ${applicationName}Module(HibernateBundle<${applicationName}Configuration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {

    }

    @Provides
    @LazySingleton
    public final Client provideClient (Environment environment, ${applicationName}Configuration configuration) {
        return new JerseyClientBuilder(environment)
                .using(configuration.getClient())
                .build("test client");
    }

    @Provides
    public final SessionFactory provideSessionFactory () {
        return hibernateBundle.getSessionFactory();
    }

}
