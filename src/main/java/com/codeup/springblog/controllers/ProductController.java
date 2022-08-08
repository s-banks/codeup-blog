package com.codeup.springblog.controllers;



import com.codeup.springblog.models.Product;
import com.codeup.springblog.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
	private ProductRepository productsDao;

	public ProductController(ProductRepository productsDao) {
		this.productsDao = productsDao;
	}

	@GetMapping("/products")
	public String showAllProducts(Model model) {
		model.addAttribute("products", productsDao.findAll());

//		System.out.println(productsDao.getProductByName("Oil - Safflower").getPrice());

		List<Product> searchedProducts = productsDao.getProductByName("oil");
		//productsDao.save() will help save post in the exercise
		for(Product product : searchedProducts) {
			System.out.println(product.getName());
		}

		return "products/index";
	}

	@GetMapping("/products/create")
	public String showCreateForm(Model model) {
		model.addAttribute("product", new Product());
		return "products/create";
	}

	@PostMapping("/products/create")
	public String create(@ModelAttribute Product product) {
		productsDao.save(product);
		return "redirect:/products";
	}

	@GetMapping("/products/edit/{id}")
	public String editProduct(Model model, @PathVariable long id) {
		model.addAttribute("product", productsDao.getById(id));
		return "products/create";
	}

}
