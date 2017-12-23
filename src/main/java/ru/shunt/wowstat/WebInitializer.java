package ru.shunt.wowstat;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import ru.shunt.wowstat.config.Config;

public class WebInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Config.getInstance(); // Reads API key from CONFIG file
		return application.sources(Application.class);
	}

}