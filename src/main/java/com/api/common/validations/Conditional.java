package com.api.common.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.BeanWrapperImpl;

import com.api.common.validations.Conditional.ConditionalValidator;
import com.api.common.validations.Conditional.Conditionals;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Repeatable(Conditionals.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConditionalValidator.class })
public @interface Conditional {

	String message() default "This field is required.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String selected();

	String[] required();

	String[] values();

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE })
	public @interface Conditionals {
		Conditional[] value();
	}

	@Slf4j
	public static class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

		private String selected;
		private String[] required;
		private String message;
		private String[] values;

		@Override
		public void initialize(Conditional requiredIfChecked) {
			selected = requiredIfChecked.selected();
			required = requiredIfChecked.required();
			message = requiredIfChecked.message();
			values = requiredIfChecked.values();
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
					for (String propName : required) {
						Object requiredValue = getProperty(objectToValidate, propName);
						valid = requiredValue != null && Objects.nonNull(requiredValue);
						if (!valid) {
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate(message).addPropertyNode(propName)
									.addConstraintViolation();
						}
					}
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