package com.api.common.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValuesAllowed.Validator.class })
public @interface ValuesAllowed {

	String message() default "Not Allowed Value";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

//	String propName();

	String[] values();

	class Validator implements ConstraintValidator<ValuesAllowed, String> {
//		private String propName;
		private String message;
		private List<String> allowable;

		@Override
		public void initialize(ValuesAllowed requiredIfChecked) {
//			this.propName = requiredIfChecked.propName();
			this.message = requiredIfChecked.message();
			this.allowable = Arrays.asList(requiredIfChecked.values());
		}

		public boolean isValid(String value, ConstraintValidatorContext context) {
			Boolean valid = value == null || this.allowable.contains(value);

			if (!valid) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
						.addConstraintViolation();
			}
			return valid;
		}
	}
}