package packageName.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import packageName.db.WandDAO;
import packageName.model.Wand;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/wand/")
@Produces(MediaType.APPLICATION_JSON)
public class WandResource {

    private final WandDAO wandDAO;

    @Inject
    public WandResource(final WandDAO wandDAO) {
        this.wandDAO = wandDAO;
    }

    @POST
    @Timed
    @UnitOfWork
    public void createWand(@Valid Wand wand) {
        wandDAO.create(wand);
    }

    @GET
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public List<Wand> getWands() {
        return wandDAO.readAll();
    }

    @GET
    @Path("{name}")
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Optional<Wand> getWandByName(@PathParam("name") final String name ) {
        return wandDAO.readByName(name);
    }

}
