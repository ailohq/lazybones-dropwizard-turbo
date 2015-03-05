package ${packageName}.resources;

import javax.ws.rs.Consumes;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import ${packageName}.db.${domainName}DAO;
import ${packageName}.model.${domainName};
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/${domainLowercaseName}/")
@Produces(MediaType.APPLICATION_JSON)
public class ${domainName}Resource {

    private final ${domainName}DAO ${domainSnakeName}DAO;

    @Inject
    public ${domainName}Resource(final ${domainName}DAO ${domainSnakeName}DAO) {
        this. ${domainSnakeName}DAO = ${domainSnakeName}DAO;
    }

    // TODO add swagger
    @POST
    @Timed
    @UnitOfWork
    @Consumes(APPLICATION_JSON)
    public void create${domainName}(@Valid ${domainName} ${domainSnakeName}) {
        ${domainSnakeName}DAO.create(${domainSnakeName});
    }

    @GET
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public List<${domainName}> getAll${domainName}() {
        return ${domainSnakeName}DAO.readAll();
    }

    @GET
    @Path("{id}")
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Optional<${domainName}> get${domainName}ById(@PathParam("id") final Integer id ) {
        return ${domainSnakeName}DAO.readById(id);
    }

}
