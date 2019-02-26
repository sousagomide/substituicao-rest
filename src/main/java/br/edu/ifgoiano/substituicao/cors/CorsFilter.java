package br.edu.ifgoiano.substituicao.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private String originPermitido = "http://localhost:4200"; // TODO: Configurar ambientes
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		servletResponse.setHeader("Acess-Control-Allow-Origin", originPermitido);
		servletResponse.setHeader("Acess-Control-Allow-Credentials", "true");
		
		if (servletRequest.getMethod().equals("OPTIONS") && servletRequest.getHeader("Origin").equals(originPermitido)) {
			servletResponse.setHeader("Acess-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			servletResponse.setHeader("Acess-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			servletResponse.setHeader("Acess-Control-Max-Age", "3600");
			
			servletResponse.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}
	
}
