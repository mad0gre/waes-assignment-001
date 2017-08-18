package victor.waes.resource;

import javax.inject.Inject;
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
import victor.waes.business.DiffBusiness;
import victor.waes.dto.DiffStatusDTO;

/**
 * Diff resource, for calculating data diff.
 */
@Path("diff")
@Produces(MediaType.APPLICATION_JSON)
public final class DiffResource {

	@Inject
	private DiffBusiness diffBusiness;
	
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
    	DiffStatusDTO statusDTO = diffBusiness.calcDiff(id);
    	
    	DiffResponseBean bean = new DiffResponseBean();
    	bean.setOffset(statusDTO.getOffset());
    	bean.setStatus(statusDTO.getStatus().name());
    	
        return Response.ok(bean).build();
    }
    
	/**
	 * Endpoint to input "right" data under a specific ID for diff calculation.
	 * 
	 * @param id
	 *            ID under the right data will be stored.
	 * @param input
	 *            Object containing the input data. Data must be Base 64 encoded.
	 * @return HTTP 200 in case of success.
	 */
    @POST
    @Path("{id}/right")
    public Response right(@PathParam("id") String id,
    		@Valid @NotNull DiffDataInputBean input) {
    	diffBusiness.saveRight(id, input.getData());
    	return Response.ok().build();
    }

	/**
	 * Endpoint to input "left" data under a specific ID for diff calculation.
	 * 
	 * @param id
	 *            ID under the left data will be stored.
	 * @param input
	 *            Object containing the input data. Data must be Base 64 encoded.
	 * @return HTTP 200 in case of success.
	 */
    @POST
    @Path("{id}/left")
    public Response left(@PathParam("id") String id,
    		@Valid @NotNull DiffDataInputBean input) {
    	diffBusiness.saveLeft(id, input.getData());
    	return Response.ok().build();
    }
}
