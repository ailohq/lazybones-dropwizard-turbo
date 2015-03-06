package ${packageName}.resources;

import javax.ws.rs.Consumes;
import com.wordnik.swagger.annotations.*;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import ${packageName}.db.${domainName}DAO;
import ${packageName}.model.${domainName};
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/${domainLowercaseName}")
@Api(value = "/${domainLowercaseName}", description = "Operation on ${domainName}")
@Produces(MediaType.APPLICATION_JSON)
public class ${domainName}Resource {

    private final ${domainName}DAO ${domainSnakeName}DAO;

    @Inject
    public ${domainName}Resource(final ${domainName}DAO ${domainSnakeName}DAO) {
        this.${domainSnakeName}DAO = ${domainSnakeName}DAO;
    }

    @POST
    @Timed
    @UnitOfWork
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Create ${domainName}")
    @ApiResponses(value = {@ApiResponse(code = 422, message = "${domainName} already exists")})
    public void create${domainName}(@Valid ${domainName} ${domainSnakeName}) {
        ${domainSnakeName}DAO.create(${domainSnakeName});
    }

    @GET
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    @ApiOperation(value = "Get all ${domainName}", response = List.class)
    public List<${domainName}> getAll${domainName}() {
        return ${domainSnakeName}DAO.readAll();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    @ApiOperation(value = "Get ${domainName} by Id", response = ${domainName}.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "${domainName} not found")})
    public Optional<${domainName}> get${domainName}ById(@ApiParam(required = true) @PathParam("id") final Integer id ) {
        return ${domainSnakeName}DAO.readById(id);
    }

}
