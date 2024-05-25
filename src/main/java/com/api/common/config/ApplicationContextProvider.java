package com.api.common.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationContextProvider
		implements ApplicationListener<ApplicationStartedEvent>, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static <T> T getEnvironmentProperty(String key, Class<T> targetClass, T defaultValue) {
		if (key == null || targetClass == null) {
			throw new NullPointerException();
		}

		T value = null;
		if (applicationContext != null) {
			value = applicationContext.getEnvironment().getProperty(key, targetClass, defaultValue);
		}
		return value;
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		log.info("<<Application context has been intialized>>:'{}'", appContext.getApplicationName());
		applicationContext = appContext;
	}

	// to get spring bean instance outside of spring context
	public static <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		log.info("Application context root:{}", applicationContext.getApplicationName());
	}
}