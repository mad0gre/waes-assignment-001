package victor.waes.bean.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import victor.waes.bean.validation.validator.Base64Validator;

/**
 * Annotation used to in bean validation to check if a String seems to be Base64
 * encoded.
 */
@Constraint(validatedBy = {Base64Validator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Base64 {

	  String message() default "Invalid Base 64 String.";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
	
}
