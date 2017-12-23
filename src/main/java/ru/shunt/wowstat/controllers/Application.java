package ru.shunt.wowstat.controllers;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("ru.shunt.wowstat")
@EntityScan("ru.shunt.wowstat")
@EnableJpaRepositories("ru.shunt.wowstat")
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public ServletRegistrationBean h2Console() {
		ServletRegistrationBean reg = new ServletRegistrationBean(new WebServlet(), "/console/*");
		reg.setLoadOnStartup(1);
		return reg;
	}

}