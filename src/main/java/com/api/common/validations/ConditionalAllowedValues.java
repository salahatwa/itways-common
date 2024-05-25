package com.api.common.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;

import com.api.common.validations.Conditional.ConditionalValidator;
import com.api.common.validations.ConditionalAllowedValues.Conditionals;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Repeatable(Conditionals.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConditionalValidator.class })
public @interface ConditionalAllowedValues {

	String message() default "Not Allowed Value";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String selected();

	String[] values();

	String field();

	String[] fieldAllowedValues();

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE })
	public @interface Conditionals {
		ConditionalAllowedValues[] value();
	}

	@Slf4j
	public static class ConditionalValidator implements ConstraintValidator<ConditionalAllowedValues, Object> {

		private String selected;
		private String field;
		private String message;
		private String[] values;
		private List<String> fieldAllowedValues;

		@Override
		public void initialize(ConditionalAllowedValues requiredIfChecked) {
			selected = requiredIfChecked.selected();
			field = requiredIfChecked.field();
			message = requiredIfChecked.message();
			values = requiredIfChecked.values();
			this.fieldAllowedValues = Arrays.asList(requiredIfChecked.fieldAllowedValues());
		}

		private Object getProperty(Object sourceObject, String propertyName) {
			BeanWrapperImpl wrapper = new BeanWrapperImpl(sourceObject);

			Object attributeValue = wrapper.getPropertyValue(propertyName);
			return attributeValue;
		}

		@Override
		public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
			Boolean valid = true;
			try {
				Object actualValue = getProperty(objectToValidate, selected);
				if (Arrays.asList(values).contains(actualValue)) {
					Object fieldValue = getProperty(objectToValidate, field);

					valid = fieldValue == null || this.fieldAllowedValues.contains(fieldValue);

					if (!valid) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(message.concat(this.fieldAllowedValues.toString()))
								.addPropertyNode(this.field).addConstraintViolation();
					}
					return valid;

				}
			} catch (Exception e) {
				log.error("Accessor method is not available for class : {}, exception : {}",
						objectToValidate.getClass().getName(), e);
				e.printStackTrace();
				return false;
			}
			return valid;
		}

	}

}