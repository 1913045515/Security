package org.security;

import org.security.config.HTTPBearerAuthorizeAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Test26SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test26SecurityApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean jwtFilterRegistrationBean(){
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
//		registrationBean.setFilter(httpBearerFilter);
//		List<String> urlPatterns = new ArrayList<String>();
//		urlPatterns.add("/user/getusers");
//		registrationBean.setUrlPatterns(urlPatterns);
//		return registrationBean;
//	}
}
