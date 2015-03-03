package packageName.resources;

import com.google.common.base.Optional;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import packageName.JsonFixtureTest;
import packageName.db.WandDAO;
import packageName.model.Wand;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LazybonesResourceTest extends JsonFixtureTest {

    private final static WandDAO WAND_DAO = mock(WandDAO.class);
    private final Wand wand = readFromJson("fixtures/wand.json", Wand.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new WandResource(WAND_DAO))
            .build();

    @Before
    public void setUp () {
        // given
        when(WAND_DAO.readByName("1")).thenReturn(Optional.of(wand));
        when(WAND_DAO.readAll()).thenReturn(Arrays.<Wand>asList(wand));
    }

    @Test
    public void createLazyBone () {

        // when
        Response response = resources.client().target("/lazybones/1").request().post(Entity.json(wand));

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
    }


    @Test
    public void getLazyBoneByName () {

        // when
        Response response = resources.client().target("/lazybones/1").request().get();
        Wand result = resources.client().target("/lazybones/1").request().get(Wand.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(result).isEqualToComparingFieldByField(wand);
    }


    @Test
    public void getAllLazyBones () {

        // when
        Response response = resources.client().target("/lazybones/").request().get();
        List<Wand> result = resources.client().target("/lazybones/").request().get(List.class);

        // then
        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0)).isEqualToComparingFieldByField(lazybone);
    }

}
