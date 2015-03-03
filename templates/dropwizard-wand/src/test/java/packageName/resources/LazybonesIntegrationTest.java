package packageName.resources;

import org.junit.Test;
import packageName.IntegrationTest;
import packageName.model.Wand;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class LazybonesIntegrationTest extends IntegrationTest {

    @Test
    public void createLazybone () {
        // given
        Wand wand = readFromJson("fixtures/wand.json", Wand.class);

        // when
        Response response = client.target(
                String.format("http://localhost:%d/wand", RULE.getLocalPort()))
                .request()
                .post(Entity.json(wand), Response.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
    }

}
