package com.example.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * @author Ravisankar C
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface RestClient {
	@Nonbinding 
	boolean useProxy() default false;
	
	@Nonbinding 
	int readTimeout() default 30000;
	
	@Nonbinding 
	int connectTimeout() default 6000;
	
	@Nonbinding 
	int maxConnectionsPerRoute() default 20;
}
