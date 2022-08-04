package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PostController {
	@RequestMapping(path = "/posts", method = RequestMethod.GET)
	public String showAllPosts(Model model) {
		model.addAttribute("posts", postsDao.findAll());
		return "posts/index";
	}

	@RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
	public String postPage(@PathVariable Long id, Model model) {
		model.addAttribute("singlePost", postsDao.getById(id));
		return "/posts/show";
	}

	@RequestMapping(path = "/posts/create", method = RequestMethod.GET)
	public String createPost() {return "posts/create";}


	@PostMapping(path = "/posts/create")
	String createPost(HttpServletRequest request) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		postsDao.save(new Post(title, body));
		return "redirect:/posts";
	}

	private final PostRepository postsDao;
	public PostController(PostRepository postsDao) {
		this.postsDao = postsDao;
	}
}
