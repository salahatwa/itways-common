package com.api.common.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateFormat.Validator.class })
public @interface DateFormat {

	String message() default "Invalid Date Format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern();

	class Validator implements ConstraintValidator<DateFormat, String> {
		private String pattern;

		@Override
		public void initialize(DateFormat constraintAnnotation) {
			this.pattern = constraintAnnotation.pattern();
		}

		@Override
		public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
			if (object == null) {
				return true;
			}

			try {
				new SimpleDateFormat(pattern).parse(object);
				return true;
			} catch (Exception e) {
//				e.printStackTrace();
				return false;
			}
		}
	}
}