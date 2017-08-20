package victor.waes.bean.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import victor.waes.bean.validation.Base64;

/**
 * Validator for Base64 Strings.
 */
public final class Base64Validator implements ConstraintValidator<Base64, String> {

	private final Pattern base64CharsPattern = Pattern.compile("^[A-Za-z0-9+\\/=]*$");
	
	@Override
	public void initialize(Base64 str) {		
		
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext context) {
		if (str != null) {
			if (str.length() % 4 == 0) {
				Matcher m = base64CharsPattern.matcher(str);
				if (m.matches()) {
					return true;
				}
			}
		}
		
		return false;
	}
}
