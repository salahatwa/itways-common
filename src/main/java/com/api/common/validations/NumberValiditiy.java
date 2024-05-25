package com.api.common.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NumberValiditiy.Validator.class })
public @interface NumberValiditiy {

	String message()

	default "Not Allowed Value";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean allowInteger() default false;

	boolean allowDouble() default false;

	boolean allowLong() default false;

	boolean allowFloat() default false;

	boolean minIntValidator() default false;

	boolean maxIntValidator() default false;

	int intMax() default 0;

	int intMin() default 0;

	class Validator implements ConstraintValidator<NumberValiditiy, String> {
		private boolean isInteger;
		private boolean isDouble;
		private boolean isLong;
		private boolean isFloat;

		private boolean minIntValidator;
		private boolean maxIntValidator;
		private int intMax;
		private int intMin;

		@Override
		public void initialize(NumberValiditiy constraintAnnotation) {
			this.isInteger = constraintAnnotation.allowInteger();
			this.isDouble = constraintAnnotation.allowDouble();
			this.isLong = constraintAnnotation.allowLong();
			this.isFloat = constraintAnnotation.allowFloat();
			this.minIntValidator = constraintAnnotation.minIntValidator();
			this.maxIntValidator = constraintAnnotation.maxIntValidator();
			this.intMax = constraintAnnotation.intMax();
			this.intMin = constraintAnnotation.intMin();
		}

		@Override
		public boolean isValid(String number, ConstraintValidatorContext constraintContext) {

			Boolean valid = number == null || number == "";

			if (number == null || number == "") {
				return valid;
			}

			try {

				if (isInteger) {
					valid = isIntegerNum(number);

					if (minIntValidator) {
						valid = Integer.parseInt(number) >= intMin;
					}

					if (maxIntValidator) {
						valid = Integer.parseInt(number) <= intMax;
					}
					return valid;
				} else if (isLong) {
					return isLongNum(number);
				} else if (isDouble) {
					return isDoubleNum(number);
				} else if (isFloat) {
					return isFloatNum(number);
				} else {
					return true;
				}

			} catch (Exception e) {
				return false;
			}
		}

		public static boolean isLongNum(final String strNum) {
			try {
				Long.parseLong(strNum);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}

		public static boolean isFloatNum(final String strNum) {
			try {
				Float.parseFloat(strNum);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}

		public static boolean isDoubleNum(final String strNum) {
			try {
				Double.parseDouble(strNum);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}

		public static boolean isIntegerNum(final String strNum) {
			try {
				Integer.parseInt(strNum);
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}
	}
}