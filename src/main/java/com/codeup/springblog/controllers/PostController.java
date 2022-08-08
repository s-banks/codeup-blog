package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

	private PostRepository postsDao;
	private UserRepository usersDao;
	private TagRepository tagDao;

	public PostController(PostRepository postsDao, UserRepository usersDao, TagRepository tagDao) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
		this.tagDao = tagDao;

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
		model.addAttribute("post", new Post());
		return "posts/create";
	}

	@PostMapping(path = "/posts/create")
	String createPost(@ModelAttribute Post post) {
		postsDao.save(post);
		return "redirect:/posts";
	}

	@GetMapping("/posts/edit/{id}")
	public String editProduct(Model model, @PathVariable long id) {
		model.addAttribute("post", postsDao.getById(id));
		return "posts/create";
	}

//	@RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
//	public String tagPage(@PathVariable Long id, Model model) {
//		model.addAttribute("tag", tagDao.getById(id));
//		return "/posts/tag";
//	}
}
