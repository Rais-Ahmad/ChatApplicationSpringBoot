package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listAllUser() {
        return categoryRepository.findAll();
    }


    public Category getCategory(long id) {
        return categoryRepository.findById(id).get();
    }

    public void saveCategory(Category category) {

        categoryRepository.save(category);

    }

}





