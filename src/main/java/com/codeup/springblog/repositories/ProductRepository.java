package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("from Product p where p.name like %:name%")
	List<Product> getProductByName(@Param("name")String name);
//	Product getProductByName(String name);
}
