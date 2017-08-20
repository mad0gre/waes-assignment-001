package victor.waes.bean.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import victor.waes.bean.validation.validator.Base64Validator;

public class Base64ValidatorTest {

	private Base64Validator validator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		validator = new Base64Validator();
	}

	@After
	public void tearDown() throws Exception {
	}	
	
	@Test
	public void isValidNullString() {
		boolean result = validator.isValid(null, null);
		assertFalse("Null string should be invalid.", result);
	}

	@Test
	public void isValidEmptyString() {
		boolean result = validator.isValid("", null);
		assertTrue("Empty string should be valid.", result);
	}
	
	@Test
	public void isValidWrongLenght() {
		boolean result = validator.isValid("WrongWrongWrong", null);
		assertFalse("String with lenght not divisible by 4 should be invalid.", result);
	}
	
	@Test
	public void isValidWrongChars() {
		boolean result = validator.isValid("this is not ok..", null);
		assertFalse("String with invalid characters should be invalid.", result);
	}
	
	@Test
	public void isValidOk() {
		boolean result = validator.isValid("0kAy+==/", null);
		assertTrue("String with correct lenght and valid chars should be valid.", result);
	}
}
