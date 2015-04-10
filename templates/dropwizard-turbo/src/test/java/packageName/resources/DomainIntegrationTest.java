package ${packageName}.resources;

import org.junit.FixMethodOrder;
import org.junit.Test;
import ${packageName}.IntegrationTest;
import ${packageName}.model.${domainName};
import org.junit.runners.MethodSorters;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class ${domainName}IntegrationTest extends IntegrationTest {

    @Test
    public void create${domainName} () {
        // when
        Response response = client.target(
                String.format("http://localhost:%d/${domainLowercaseName}", RULE.getLocalPort()))
                .request()
                .post(Entity.json(given${domainName}WithId(null)), Response.class);

        // then
        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());

        ${domainName} ${domainSnakeName} = response.readEntity(${domainName}.class);
        assertThat(${domainSnakeName}.getId()).isNotNull();
        assertThat(${domainSnakeName}).isEqualToComparingFieldByField(given${domainName}WithId(1L));
    }

    @Test
    public void getAll${domainName} () {
        // when
        List<${domainName}> ${domainSnakeName}List = client.target(
                String.format("http://localhost:%d/${domainLowercaseName}", RULE.getLocalPort()))
                .request()
                .get(List.class);

        // then
        assertThat(${domainSnakeName}List).hasSize(1);
    }

    @Test
    public void get${domainName}ById () {
        // given
        ${domainName} ${domainSnakeName} = readFromJson("fixtures/${domainSnakeName}.json", ${domainName}.class);

        // when
        ${domainName} ${domainName}Result = client.target(
                String.format("http://localhost:%d/${domainLowercaseName}/1", RULE.getLocalPort()))
                .request()
                .get(${domainName}.class);

        // then
        assertThat(${domainName}Result).isEqualToIgnoringGivenFields(${domainSnakeName}, "id");
    }

}
