package victor.waes.resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import victor.waes.bean.DiffDataInputBean;
import victor.waes.bean.DiffResponseBean;
import victor.waes.business.DiffBusiness;
import victor.waes.dto.DiffStatusDTO;
import victor.waes.dto.DiffStatusDTO.DiffStatus;

@RunWith(MockitoJUnitRunner.class)
public class DiffResourceTest {
	
	@Mock private DiffBusiness diffBusiness;
	
	@InjectMocks private DiffResource diffResource = new DiffResource();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDiffHttpStatus() {
		DiffStatusDTO dummy = new DiffStatusDTO(15, DiffStatus.DIFFERENT_SIZE);
		when(diffBusiness.calcDiff(anyString())).thenReturn(dummy);
		
		Response response = diffResource.diff("171");
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testDiffStatus() {
		DiffStatusDTO dummy = new DiffStatusDTO(15, DiffStatus.DIFFERENT_SIZE);
		when(diffBusiness.calcDiff(anyString())).thenReturn(dummy);
		
		Response response = diffResource.diff("171");
		
		DiffResponseBean responseBean = (DiffResponseBean) response.getEntity();
		
		assertEquals(dummy.getStatus().name(), responseBean.getStatus());
	}
	
	@Test
	public void testDiffOffset() {
		DiffStatusDTO dummy = new DiffStatusDTO(15, DiffStatus.DIFFERENT_SIZE);
		when(diffBusiness.calcDiff(anyString())).thenReturn(dummy);
		
		Response response = diffResource.diff("171");
		
		DiffResponseBean responseBean = (DiffResponseBean) response.getEntity();
		
		assertEquals(dummy.getOffset(), responseBean.getOffset().intValue());
	}

	@Test
	public void testRightSuccess() {
		when(diffBusiness.saveRight(anyString(), anyString())).thenReturn(true);
		
		DiffDataInputBean bean = new DiffDataInputBean();
		bean.setData("1111");
		
		Response response = diffResource.right("1", bean);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testRightFail() {
		when(diffBusiness.saveRight(anyString(), anyString())).thenReturn(false);
		
		DiffDataInputBean bean = new DiffDataInputBean();
		bean.setData("1111");
		
		Response response = diffResource.right("1", bean);
		
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}

	@Test
	public void testLeftSuccess() {
		when(diffBusiness.saveLeft(anyString(), anyString())).thenReturn(true);
		
		DiffDataInputBean bean = new DiffDataInputBean();
		bean.setData("1111");
		
		Response response = diffResource.left("1", bean);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testLeftFail() {
		when(diffBusiness.saveLeft(anyString(), anyString())).thenReturn(false);
		
		DiffDataInputBean bean = new DiffDataInputBean();
		bean.setData("1111");
		
		Response response = diffResource.left("1", bean);
		
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}

}
