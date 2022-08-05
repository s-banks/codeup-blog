package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
