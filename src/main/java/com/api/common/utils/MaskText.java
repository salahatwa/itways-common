package com.api.common.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ssatwa
 *
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskText {

	/**
	 * 
	 * @return default mask value
	 */
	String value() default "*****";

	float valueFloat() default 00000;

	int valueInt() default 0;
}