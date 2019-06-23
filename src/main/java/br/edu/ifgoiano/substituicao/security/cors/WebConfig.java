package br.edu.ifgoiano.substituicao.security.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	private String[] allowedOrigins = { "localhost:4200" };
	private String[] allowedMethods = { "POST", "GET", "DELETE", "PUT", "OPTIONS" };
	private String[] allowedHeaders = { "Authorization", "Content-Type", "Accept" };
	private Long maxAge = 3600L;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(allowedOrigins)
			.allowedMethods(allowedMethods)
			.allowedHeaders(allowedHeaders)
			.maxAge(maxAge);
	}
	
}
