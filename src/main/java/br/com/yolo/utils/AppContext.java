package br.com.yolo.utils;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.yolo.user.UserVerificationEntity;

/**
* @author  Chrisitan Chiconato
* @since   2018-02-10
*/

@Configuration
@EnableAutoConfiguration
@EnableCaching
@Controller
@ComponentScan (basePackages = {"br.com.yolo"})
public class AppContext {
  
  	@Bean(name = "userVerificationEntity")
	  public UserVerificationEntity getUserVerificationEntity() {
	  	  return new UserVerificationEntity();
	  }
  	
	@Bean(name = "userVerificationEmailSender")
	  public EmailSender getUserVerificationEmailSender() {
	  	  return new EmailSender();
	  }
  	
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(-100000);
		return bean;
	}	
}
