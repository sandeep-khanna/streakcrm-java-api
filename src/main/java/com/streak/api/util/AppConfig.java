package com.streak.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * @author Sandeep Khanna
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Configuration("StreakCrmApi")
@ComponentScan("com.streak")
@EnableCaching
@PropertySources({
	@PropertySource(value="classpath:streakcrmapi.properties"),
	@PropertySource(value="file:./config/streakcrmapi.properties", ignoreResourceNotFound=true),
	@PropertySource(value="file:./streakcrmapi.properties", ignoreResourceNotFound=true),
	@PropertySource(value="classpath:config/streakcrmapi.properties", ignoreResourceNotFound=true),
	@PropertySource(value="classpath:streakcrmapi.properties", ignoreResourceNotFound=true)
})
@ConfigurationProperties
public class AppConfig extends CachingConfigurerSupport {
	
	public enum ApiVersion {
		VERSION_1("1");
		
		public String version;
		
		private ApiVersion(String version) {
			this.version = version;
		}
		
		public boolean in(ApiVersion... apiVersions) {
			return Arrays.stream(apiVersions).anyMatch(apiVersion -> apiVersion == this);
		}
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	private ApiVersion apiVersion = ApiVersion.VERSION_1;
	private String apiKey = null;

	@Autowired
	LoggingSystem loggingSystem;

	@Bean
	@Primary
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter){        
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(mappingJackson2HttpMessageConverter);
	    RestTemplate restTemplate = restTemplateBuilder.requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
	                                                .messageConverters(messageConverters)
	                                                .build();
	    return restTemplate;
	}
	
    @Bean
    @Autowired
    public HandlerInstantiator handlerInstantiator(ApplicationContext applicationContext) {
        return new SpringHandlerInstantiator(applicationContext.getAutowireCapableBeanFactory());
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(HandlerInstantiator handlerInstantiator) {
        Jackson2ObjectMapperBuilder result = new Jackson2ObjectMapperBuilder();
        result.handlerInstantiator(handlerInstantiator);
        return result;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder objectMapperBuilder) {
        return new MappingJackson2HttpMessageConverter(objectMapperBuilder.build());
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
    	SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("insightlyCustomFieldMetadataCache"),
        									new ConcurrentMapCache("insightlyLeadsCache"),
        									new ConcurrentMapCache("insightlyOrganisationsCache")));
        return cacheManager;
    }
    
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
    	return new SimpleKeyGenerator();
    }
}