package com.mintic.tiendagenerica.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/tiendagenerica/swagger")
public class RootRestController {

	@Value("#{servletContext.contextPath}")
	private String servletContextPath;

	@ApiIgnore
	@RequestMapping(value = "/")
	public void redirectToSwagger(HttpServletResponse response) throws IOException {
		response.sendRedirect(this.servletContextPath + "/swagger-ui.html");
	}
}