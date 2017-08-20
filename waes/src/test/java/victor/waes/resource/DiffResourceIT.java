package victor.waes.resource;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import victor.waes.application.V1Application;
import victor.waes.bean.DiffDataInputBean;
import victor.waes.bean.DiffResponseBean;
import victor.waes.dto.DiffStatusDTO.DiffStatus;

public class DiffResourceIT extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new V1Application();
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testRightHttpResponse() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		Response response = target("/diff/1/right").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testLeftHttpResponse() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		Response response = target("/diff/1/left").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testDiffHttpResponse() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		target("/diff/1/right").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		target("/diff/1/left").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		Response response = target("/diff/1").request().get();
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testDiffJsonResponse() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		target("/diff/1/right").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		target("/diff/1/left").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		Response response = target("/diff/1").request().get();
		
		DiffResponseBean responseBean = response.readEntity(DiffResponseBean.class);
		
		assertNotNull("Response bean should not be null.", responseBean);
	}
	
	@Test
	public void testDiffJsonResponseStatus() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		target("/diff/1/right").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		target("/diff/1/left").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		Response response = target("/diff/1").request().get();
		
		DiffResponseBean responseBean = response.readEntity(DiffResponseBean.class);
		
		assertEquals(DiffStatus.EQUAL.name(), responseBean.getStatus());
	}
	
	@Test
	public void testDiffJsonResponseOffset() {
		DiffDataInputBean input = new DiffDataInputBean();
		input.setData("test");
		
		target("/diff/1/right").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		target("/diff/1/left").request().post(Entity.entity(input, MediaType.APPLICATION_JSON));
		Response response = target("/diff/1").request().get();
		
		DiffResponseBean responseBean = response.readEntity(DiffResponseBean.class);
		
		assertEquals(0, responseBean.getOffset().intValue());
	}
}
