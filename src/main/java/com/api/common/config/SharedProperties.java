package com.api.common.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.Data;

@Data
@PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "application-common.properties")
@EnableEncryptableProperties
@Component
public class SharedProperties {

}
