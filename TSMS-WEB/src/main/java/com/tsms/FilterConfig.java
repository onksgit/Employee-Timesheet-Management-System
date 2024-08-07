package com.tsms;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tsms.web.filter.FilterRequest;
import com.tsms.web.servlet.ImageReadServlet;

@Configuration
@ComponentScan("com")
@EnableAutoConfiguration
public class FilterConfig {

	@Bean
    FilterRegistrationBean<FilterRequest> generalFilterRegistration() {
        FilterRegistrationBean<FilterRequest> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FilterRequest());
        registrationBean.addUrlPatterns("*.html");
        registrationBean.setName("FilterRequest");
        return registrationBean;
    }
	
	@Bean
	ServletRegistrationBean<ImageReadServlet> imageServletRegistration() {
		ServletRegistrationBean<ImageReadServlet> registrationBean = new ServletRegistrationBean<>(
				new ImageReadServlet(), "/ImageReadServlet");
		registrationBean.setName("ImageReadServlet");
		return registrationBean;
	}
}
