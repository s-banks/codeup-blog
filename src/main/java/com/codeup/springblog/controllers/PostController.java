package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

	private PostRepository postsDao;
	private UserRepository usersDao;

	public PostController(PostRepository postsDao, UserRepository usersDao) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
	}

	@RequestMapping(path = "/posts", method = RequestMethod.GET)
	public String showAllPosts(Model model) {
		model.addAttribute("posts", postsDao.findAll());
		return "posts/index";
	}

	@RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
	public String postPage(@PathVariable Long id, Model model) {
		model.addAttribute("post", postsDao.getById(id));
		return "/posts/show";
	}

	@RequestMapping(path = "/posts/create", method = RequestMethod.GET)
	public String createPost(Model model) {
		model.addAttribute("users", usersDao.findAll());
		return "posts/create";
	}

	@PostMapping(path = "/posts/create")
	String createPost(@RequestParam(name = "user") long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
		postsDao.save(new Post(title, body, usersDao.getById(id)));
		return "redirect:/posts";
	}
}
