package com.chatapplicationspringBoot.Controller;


import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    final
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(" ")
    public ResponseEntity<Object> userList(/*@RequestHeader("Authorization") String authValue*/) {
        List<Category> categoryList = categoryService.listAllUser();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Category category) {

        categoryService.saveCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);


    }

}
