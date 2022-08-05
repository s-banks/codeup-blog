package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {
}
