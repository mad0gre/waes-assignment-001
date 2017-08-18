package victor.waes.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import victor.waes.bean.DiffDataInputBean;
import victor.waes.bean.DiffResponseBean;

/**
 * Diff resource, for calculating data diff.
 */
@Path("diff")
@Produces(MediaType.APPLICATION_JSON)
public final class DiffResource {

	/**
	 * Endpoint to check the data diff under a path specified ID.
	 * 
	 * @param id
	 *            ID of the diff calculation.
	 * @return A {@link DiffResponseBean} object.
	 */
    @GET
    @Path("{id}")
    public Response diff(@PathParam("id") String id) {
    	DiffResponseBean bean = new DiffResponseBean(id);
    	
        return Response.ok(bean).build();
    }
    
    @POST
    @Path("{id}/right")
    public Response right(@PathParam("id") String id,
    		@Valid @NotNull DiffDataInputBean input) {
    	return Response.ok().build();
    }
    
    @POST
    @Path("{id}/left")
    public Response left(@PathParam("id") String id,
    		@Valid @NotNull DiffDataInputBean input) {
    	return Response.ok().build();
    }
}
