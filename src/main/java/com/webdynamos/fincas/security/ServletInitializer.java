package com.webdynamos.fincas.security;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.webdynamos.fincas.FincasApplication;

// import com.webdynamos.fincas.services.JwtApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FincasApplication.class);
	}

}