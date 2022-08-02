package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
	@RequestMapping(path = "/post", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "<h1>post index page</h1>";
	}

	@RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String postPage(@PathVariable int id) {
		return String.format("Single post page for post#: %d", id);
	}

	@RequestMapping(path = "/post/create", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {return "<form action=\"/post/create\" method=\"post\"><label for=\"name\">Name:</label><br><input type=\"text\" id=\"name\" name=\"name\"><br><input type=\"submit\" value=\"Submit\">";}


	@RequestMapping(path = "/post/create", method = RequestMethod.POST)
	@ResponseBody
	String handleRequest(HttpServletRequest request) {
		String user = request.getParameter("name");
		return String.format("<h1>You entered the name: %s</h1>", user);
	}

}
