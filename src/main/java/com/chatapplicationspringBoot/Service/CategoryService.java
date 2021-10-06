package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription List of Categories
     * @return
     */
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get Category by Id
     * @param id
     * @return
     */
    public Category getCategory(long id) {
        return categoryRepository.findById(id).get();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Create a Category
     * @param category
     */
    public void saveCategory(Category category) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        category.setCategoryDate(dtf.format(now));
        categoryRepository.save(category);

    }

    public void updateCategory(Category category){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        category.setCategoryDate(dtf.format(now));
        categoryRepository.save(category);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}





