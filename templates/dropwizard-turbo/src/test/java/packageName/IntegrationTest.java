package ${packageName};

import com.google.common.io.Resources;
import com.squarespace.jersey2.guice.BootstrapUtils;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class IntegrationTest extends JsonFixtureTest {

    @ClassRule
    public static final DropwizardAppRule<${applicationName}Configuration> RULE =
            new DropwizardAppRule<>(${applicationName}Application.class, resourceFilePath("${applicationDashName}-test.yml"));

    protected static Client client;

    @BeforeClass
    public static void setUp() {
        client = new JerseyClientBuilder(RULE.getEnvironment())
                .using(RULE.getConfiguration().getClient())
                .build("test client");
    }

    public static String resourceFilePath(String resourceClassPathLocation) {
        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        dropH2Database();
        BootstrapUtils.reset();
    }

    private static void dropH2Database() throws SQLException {
        RULE.getConfiguration().getDataSource();

        DataSource ds = JdbcConnectionPool.create(
                RULE.getConfiguration().getDataSource().getUrl(),
                RULE.getConfiguration().getDataSource().getUser(),
                RULE.getConfiguration().getDataSource().getPassword());
        Connection conn = ds.getConnection();
        conn.createStatement().executeUpdate("DROP ALL OBJECTS");
        conn.close();
    }
}
