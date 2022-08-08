package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

	private PostRepository postsDao;
	private UserRepository usersDao;
	private TagRepository tagDao;
	private EmailService emailService;
	//private UserRepository getUsersDao;

	public PostController(PostRepository postsDao, UserRepository usersDao, TagRepository tagDao, EmailService emailService) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
		this.tagDao = tagDao;
		this.emailService = emailService;
		//this.getUsersDao = getUsersDao;
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
	String createPost(@ModelAttribute Post post, Model model) {
		postsDao.save(post);
		 User user2 = post.getUser();
		 String user3 = user2.getUsername();
		model.addAttribute("users", usersDao.findAll());
		emailService.prepareAndSend(usersDao.findByUsername(user3), "Post Created", "Your new post has been created.");
		return "redirect:/posts";
	}

	@GetMapping("/posts/edit/{id}")
	public String editProduct(Model model, @PathVariable long id) {
		model.addAttribute("users", usersDao.findAll());
		model.addAttribute("post", postsDao.getById(id));
		return "posts/create";
	}

//	@RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
//	public String tagPage(@PathVariable Long id, Model model) {
//		model.addAttribute("tag", tagDao.getById(id));
//		return "/posts/tag";
//	}
}
