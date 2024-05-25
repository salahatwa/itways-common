package com.api.common.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateValiditiy.Validator.class })
public @interface DateValiditiy {

	String message() default "{message.key}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String pattern();

	boolean allowDatePast() default true;

	boolean allowDateToday() default true;

	boolean allowDateFuture() default true;

	class Validator implements ConstraintValidator<DateValiditiy, String> {
		private String pattern;
		private boolean isDatePast;
		private boolean isDateToday;
		private boolean isDateFuture;

		@Override
		public void initialize(DateValiditiy constraintAnnotation) {
			this.pattern = constraintAnnotation.pattern();
			this.isDatePast = constraintAnnotation.allowDatePast();
			this.isDateToday = constraintAnnotation.allowDateToday();
			this.isDateFuture = constraintAnnotation.allowDateFuture();
		}

		@Override
		public boolean isValid(String date, ConstraintValidatorContext constraintContext) {
			if (date == null) {
				return true;
			}

			try {

				if (!isDateFuture) {
					return !isDateFuture(date, pattern);
				} else if (!isDateToday) {
					return !isDateToday(date, pattern);
				} else if (!isDatePast) {
					return !isDatePast(date, pattern);
				} else {
					return true;
				}

			} catch (Exception e) {
//				e.printStackTrace();
				return false;
			}
		}

		public static boolean isDatePast(final String date, final String dateFormat) {
			LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate inputDate = LocalDate.parse(date, dtf);

			return inputDate.isBefore(localDate);
		}

		public static boolean isDateToday(final String date, final String dateFormat) {
			LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate inputDate = LocalDate.parse(date, dtf);

			return inputDate.isEqual(localDate);
		}

		public static boolean isDateFuture(final String date, final String dateFormat) {
			LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			LocalDate inputDate = LocalDate.parse(date, dtf);

			return inputDate.isAfter(localDate);
		}
	}
}