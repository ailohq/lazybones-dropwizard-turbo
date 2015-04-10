package ${packageName}.resources;

import java.util.Optional;
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

import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;

@RunWith(MockitoJUnitRunner.class)
public class ${domainName}ResourceTest extends JsonFixtureTest {

    private final static ${domainName}DAO ${domainSnakeName}DAO = mock(${domainName}DAO.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ${domainName}Resource(${domainSnakeName}DAO))
            .addProvider(OptionalMessageBodyWriter.class)
            .addProvider(OptionalParamFeature.class)
            .build();

    @Before
    public void setUp () {
    }

    @Test
    public void create${domainName} () {
        // given
        when(${domainSnakeName}DAO.create(any(${domainName}.class))).thenReturn(given${domainName}WithId(1L));

        // when
        Response response = resources.client().target("/${domainLowercaseName}").request().post(Entity.json(given${domainName}WithId(null)));

        // then
        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());

        ${domainName} ${domainSnakeName} = response.readEntity(${domainName}.class);
        assertThat(${domainSnakeName}.getId()).isNotNull();
        assertThat(${domainSnakeName}).isEqualToComparingFieldByField(given${domainName}WithId(1L));
    }


    @Test
    public void get${domainName}ById () {
        // given
        when(${domainSnakeName}DAO.readById(1L)).thenReturn(Optional.of(given${domainName}WithId(1L)));

        // when
        Response response = resources.client().target("/${domainLowercaseName}/1").request().get();

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());

        ${domainName} ${domainSnakeName} = response.readEntity(${domainName}.class);
        assertThat(${domainSnakeName}).isEqualToComparingFieldByField(given${domainName}WithId(1L));
    }


    @Test
    public void getAll${domainName} () {
        // given
        when(${domainSnakeName}DAO.readAll()).thenReturn(Arrays.<${domainName}>asList(
            given${domainName}WithId(1L),
            given${domainName}WithId(2L)
        ));

        // when
        Response response = resources.client().target("/${domainLowercaseName}").request().get();
        List<${domainName}> result = resources.client().target("/${domainLowercaseName}").request().get(List.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(result.size()).isEqualTo(2);
    }
}
