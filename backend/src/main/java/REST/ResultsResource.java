package REST;


import Beans.ResultsBean;
import Models.CheckResponse;
import Models.Coordinates;
import Models.Result;
import REST.AuthFilter.Secured;
import REST.AuthFilter.UserPrincipal;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;


@Stateless
@Path("/results")
public class ResultsResource {
    @EJB
    private ResultsBean resultsBean;

    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResults(
            @Context SecurityContext securityContext
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        List<Result> results = resultsBean.getResults(user);
        return Response.ok().entity(results).build();
    }

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCoordinates(
            @Context SecurityContext securityContext,
            Coordinates coordinates
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        CheckResponse response = resultsBean.check(coordinates, user);
        return Response.ok().entity(response).build();
    }

    @Secured
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response clearResults(
            @Context SecurityContext securityContext
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        boolean success = resultsBean.clearResults(user);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
