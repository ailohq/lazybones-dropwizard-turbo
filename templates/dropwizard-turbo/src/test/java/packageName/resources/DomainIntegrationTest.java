package ${packageName}.resources;

import org.junit.Test;
import ${packageName}.IntegrationTest;
import ${packageName}.model.${domainName};

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class ${domainName}IntegrationTest extends IntegrationTest {

    @Test
    public void createLazybone () {
        // given
        ${domainName} ${domainSnakeName} = readFromJson("fixtures/${domainSnakeName}.json", ${domainName}.class);

        // when
        Response response = client.target(
                String.format("http://localhost:%d/${domainName}", RULE.getLocalPort()))
                .request()
                .post(Entity.json(${domainSnakeName}), Response.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
    }

}
