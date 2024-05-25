package com.api.common.utils;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

/**
 * @author ssatwa
 * @date 2020-03-16
 */
public class CustomIdGenerator implements IdentifierGenerator, Configurable {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		Object id = ReflectionUtils.getFieldValue("id", object);
		if (id != null) {
			return (Serializable) id;
		}
		return generate(session, object);
	}
}
