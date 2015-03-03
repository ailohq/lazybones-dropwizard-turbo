package packageName;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class ApplicationModule extends AbstractModule {

    private final HibernateBundle hibernateBundle;

    public ApplicationModule(HibernateBundle<SampleApplicationConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {

    }

//    @Provides
//    @Singleton
//    public final ScanningHibernateBundle provideScanningHibernateBundle () {
//        return new ScanningHibernateBundle<SampleApplicationConfiguration>("packageName.model") {
//            @Override
//            public DataSourceFactory getDataSourceFactory(SampleApplicationConfiguration configuration) {
//                return configuration.getDataSource();
//            }
//        };
//    }

    @Provides
    public final SessionFactory provideSessionFactory () {
        return hibernateBundle.getSessionFactory();
    }

}
