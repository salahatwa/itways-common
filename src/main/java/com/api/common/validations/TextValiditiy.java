package com.api.common.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TextValiditiy.Validator.class })
public @interface TextValiditiy {

	String message() default "Invalid Character";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int length() default 20;

	class Validator implements ConstraintValidator<TextValiditiy, String> {

		private static final Character[] INVALID_WINDOWS_SPECIFIC_CHARS = { '"', '*', '<', '>', '?', '|', '/', '\\',
				'#', '$', '@', '!', '(', ')' };
		private static final Character[] INVALID_UNIX_SPECIFIC_CHARS = { '\000' };
		private int length;

		@Override
		public void initialize(TextValiditiy constraintAnnotation) {
			length = constraintAnnotation.length();
		}

		@Override
		public boolean isValid(String text, ConstraintValidatorContext constraintContext) {

			try {
				return isValidText(text, length);
			} catch (Exception e) {
				return true;
			}
		}

		public static boolean isValidText(String name, int length) {
			if (StringUtils.isBlank(name)) {
				return true;
			}

			if (name.length() > length) {
				return false;
			}

			return Arrays.stream(getInvalidCharsByOS()).noneMatch(ch -> name.contains(ch.toString()));
		}

		public static Character[] getInvalidCharsByOS() {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				return INVALID_WINDOWS_SPECIFIC_CHARS;
			} else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
				return INVALID_UNIX_SPECIFIC_CHARS;
			} else {
				return new Character[] {};
			}
		}
	}
}