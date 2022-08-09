package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

	private PostRepository postsDao;
	private UserRepository usersDao;
	private TagRepository tagDao;
	private EmailService emailService;


	public PostController(PostRepository postsDao, UserRepository usersDao, TagRepository tagDao, EmailService emailService) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
		this.tagDao = tagDao;
		this.emailService = emailService;
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
	public String createPost(Model model) throws NullPointerException {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getUsername() != null) {
			model.addAttribute("post", new Post());
			return "posts/create";
		} else {
			return "login";
		}
	}

	@PostMapping(path = "/posts/create")
	String createPost(@ModelAttribute Post post, Model model) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setUser(currentUser);
		postsDao.save(post);
		emailService.prepareAndSend(usersDao.findByUsername(currentUser.getUsername()), "Post Created", "Your new post has been created.");
		return "redirect:/posts";
	}

	@GetMapping("/posts/edit/{id}")
	public String editProduct(Model model, @PathVariable long id) {
		model.addAttribute("users", usersDao.findAll());
		model.addAttribute("post", postsDao.getById(id));
		return "posts/create";
	}

}
