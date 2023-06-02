package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Category;

public interface CategoryRepository extends ListCrudRepository<Category, String>{
    
}
