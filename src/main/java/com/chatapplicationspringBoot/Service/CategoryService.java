package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Object> listAllCategories() {
        //return categoryRepository.findAll();
        List<Category> categorys = categoryRepository.findAllByStatus(true);
        if (categorys.isEmpty()) {
            return new ResponseEntity<>("Message:  Categorys are empty", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categorys, HttpStatus.OK);
        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get Category by Id
     * @param id
     * @return
     */

    public ResponseEntity<Object> getCategory(Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isPresent())
                return new ResponseEntity<>(category, HttpStatus.FOUND);
            else
                return new ResponseEntity<>("could not found category , Check id", HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>("Unable to find Category, an error has occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

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

    public ResponseEntity<Object> deleteCategoryById(long id){
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()){
                category.get().setStatus(false);
                categoryRepository.saveAll(categoryRepository.findAllById(Collections.singleton(id)));

                return new ResponseEntity("Deleted", HttpStatus.OK);
            }
            else return new ResponseEntity<>("ID does not Exist",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity("Database error",HttpStatus.NOT_FOUND);
        }
    }


}





