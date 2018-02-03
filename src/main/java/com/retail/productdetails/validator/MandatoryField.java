package com.retail.productdetails.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface defines the Mandatory filed validation.
 * @author Joyappan
 *
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MandatoryFieldValidator.class })
@Documented
public @interface MandatoryField {
	String message() default "{Mandatory field value missing}";
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
