package com.github.pyrolistical.controller;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

	@RequestMapping("/")
	public void getRoot(@RequestBody final String body, final Writer writer) throws IOException {
		writer.append("Look ma!  No XML!");
	}
}
