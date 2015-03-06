package ${packageName}.resources;

import com.google.common.base.Optional;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ${packageName}.JsonFixtureTest;
import ${packageName}.db.${domainName}DAO;
import ${packageName}.model.${domainName};

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ${domainName}ResourceTest extends JsonFixtureTest {

    private final static ${domainName}DAO ${domainSnakeName}DAO = mock(${domainName}DAO.class);
    private final ${domainName} ${domainSnakeName} = readFromJson("fixtures/${domainSnakeName}.json", ${domainName}.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ${domainName}Resource(${domainSnakeName}DAO))
            .build();

    @Before
    public void setUp () {
        // given
        when(${domainSnakeName}DAO.readById(1)).thenReturn(Optional.of(${domainSnakeName}));
        when(${domainSnakeName}DAO.readAll()).thenReturn(Arrays.<${domainName}>asList(${domainSnakeName}));
    }

    @Test
    public void create${domainName} () {

        // when
        resources.client().target("/${domainLowercaseName}").request().post(Entity.json(${domainSnakeName}));

        // then
        verify(${domainSnakeName}DAO).create(any(${domainName}.class));
    }


    @Test
    public void get${domainName}ById () {

        // when
        Response response = resources.client().target("/${domainLowercaseName}/1").request().get();
        ${domainName} result = resources.client().target("/${domainLowercaseName}/1").request().get(${domainName}.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(result).isEqualToComparingFieldByField(${domainSnakeName});
    }


    @Test
    public void getAll${domainName} () {

        // when
        Response response = resources.client().target("/${domainLowercaseName}").request().get();
        List<${domainName}> result = resources.client().target("/${domainLowercaseName}").request().get(List.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(result.size()).isEqualTo(1);
    }

}
