package com.codeup.springblog.controllers;



import com.codeup.springblog.models.Product;
import com.codeup.springblog.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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



}
