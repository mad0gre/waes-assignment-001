package victor.waes.business.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import victor.waes.dto.DiffStatusDTO;
import victor.waes.dto.DiffStatusDTO.DiffStatus;

public class DiffBusinessSimpleImplTest {
	
	private static final String DUMMY_ID = "dummy";
	
	private DiffBusinessSimpleImpl business;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		business = new DiffBusinessSimpleImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveRightNullString() {
		boolean result = business.saveRight("any", null);
		assertFalse("Shouldn't save null string.", result);
	}
	
	@Test
	public void saveRightEmptyString() {
		boolean result = business.saveRight("001", "");
		assertTrue("Should save empty string.", result);
	}

	@Test
	public void saveRightInvalidBase64() {
		boolean result = business.saveRight("", "re-");
		assertFalse("Shouldn't save invalid Base64.", result);
	}
	
	@Test
	public void testSaveRightIdNull() {
		boolean result = business.saveRight(null, "ok==");
		assertFalse("Shouldn't save valid data under null ID.", result);
	}
	
	@Test
	public void testSaveRightOk() {
		boolean result = business.saveRight("sugoi", "ok==");
		assertTrue("Should save valid Base64 string.", result);
	}

	@Test
	public void saveLeftNullString() {
		boolean result = business.saveLeft("...", null);
		assertFalse("Shouldn't save null string.", result);
	}
	
	@Test
	public void saveLeftEmptyString() {
		boolean result = business.saveLeft("--", "");
		assertTrue("Should save empty string.", result);
	}

	@Test
	public void saveLeftInvalidBase64() {
		boolean result = business.saveLeft("", "\\//=");
		assertFalse("Shouldn't save invalid Base64.", result);
	}
	
	@Test
	public void testSaveLeftIdNull() {
		boolean result = business.saveLeft(null, "");
		assertFalse("Shouldn't save valid data under null ID.", result);
	}
	
	@Test
	public void testSaveLeftOk() {
		boolean result = business.saveLeft("id", "ZHVyZXph");
		assertTrue("Should save valid Base64 string.", result);
	}
	
	@Test
	public void testCalcDiffNoRight() {
		business.saveLeft(DUMMY_ID, "cXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYQ==");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(DiffStatus.NO_RIGHT_DATA, dto.getStatus());
	}

	@Test
	public void testCalcDiffNoLeft() {
		business.saveRight(DUMMY_ID, "cXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYQ==");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(DiffStatus.NO_LEFT_DATA, dto.getStatus());
	}
	
	@Test
	public void testCalcDiffBothNull() {
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		assertEquals(DiffStatus.NO_RIGHT_DATA, dto.getStatus());
	}
	
	@Test
	public void testCalcDiffDifferentSize() {
		business.saveRight(DUMMY_ID, "cXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYS4uLg==");
		business.saveLeft(DUMMY_ID, "cXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYQ==");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(DiffStatus.DIFFERENT_SIZE, dto.getStatus());
	}
	
	@Test
	public void testCalcDiffNotEqual() {
		business.saveRight(DUMMY_ID, "DCx5CF9YS28=");
		business.saveLeft(DUMMY_ID, "DCxkCF9YS28=");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(DiffStatus.NOT_EQUAL, dto.getStatus());
	}
	
	@Test
	public void testCalcDiffNotEqualCheckOffset() {
		business.saveRight(DUMMY_ID, "DCx5CF9YS28=");
		business.saveLeft(DUMMY_ID, "DCxkCF9YS18=");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(2, dto.getOffset());
	}
	
	@Test
	public void testCalcDiffEqual() {
		business.saveRight(DUMMY_ID, "UXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYS4=");
		business.saveLeft(DUMMY_ID, "UXVhbmRvIG8gY2ludG8gYXBlcnRhIGEgYmFuaGEgcHVsYS4=");
		DiffStatusDTO dto = business.calcDiff(DUMMY_ID);
		
		assertEquals(DiffStatus.EQUAL, dto.getStatus());
	}
}
