package br.edu.ifgoiano.substituicao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubstituicaoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubstituicaoRestApplication.class, args);
	}
	
	/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/dbsubstituicao?useTimezone=true&serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}*/
}
