package com.chatapplicationspringBoot.Controller;


import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private String key = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";

    public boolean authorization(String checkKey) {
        if (checkKey.equals(key)) {
            return true;
        } else return false;
    }
    final
    /**
     * @param CategoryService
     * @Auther Rais Ahmad
     * @date 29/09/2021
     */
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get All Categories
     * @return
     */
    @GetMapping("/allCategories")
    public ResponseEntity<Object> categoryList(@RequestHeader("Authorization") String authValue) {

        if (authorization(authValue)) {
           // List<Category> categoryList = categoryService.listAllCategories();
            return categoryService.listAllCategories();
//            if (categoryList.isEmpty()) {
//                LOG.info("No data available");
//                return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
//            } else {
//                LOG.info("List of Categories : " + categoryList +" ");
//                return new ResponseEntity<>(categoryList, HttpStatus.OK);
//            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add Category
     * @param category
     * @return
     */
    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestHeader("Authorization") String authValue, @RequestBody Category category) {

        if (authorization(authValue)) {
            categoryService.saveCategory(category);
            LOG.info("Category : " + category +" added successfully ");
            return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);

        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Update Category
     * @param category
     * @return
     */
    @PutMapping("/updateCategory")
    public ResponseEntity<Object> updateCategory(@RequestHeader("Authorization") String authValue, @RequestBody Category category) {

        if (authorization(authValue)) {
            try {
                categoryService.updateCategory(category);
                LOG.info("Category updated successfully:  " + category);
                return new ResponseEntity<>("Category updated successfully ", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Category not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * @Author Rais
     * @Date 09-06-2021
     * @Discription Delete Category
     * @param id
     * @return
     */
    @DeleteMapping("/deleteCategory/{id}")

    public ResponseEntity<String> deleteCategory(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {

        if (authorization(authValue)) {
            try {
                categoryService.deleteCategory(id);
                LOG.info("Category: " + id + " deleted successfully!");
                return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
            }

        } else{
            LOG.info("Not Authorized User!");
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<Object> getCategory(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {
        if (authorization(authValue)) {
            try {
             //   Category category = categoryService.getCategory(id);
                LOG.info("user id is: " + id);
               // return new ResponseEntity<>(category, HttpStatus.OK);
                return categoryService.getCategory(id);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Category not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }
    }

    @DeleteMapping("/deleteC/{id}")
    public ResponseEntity deleteCategoryById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {

            return categoryService.deleteCategoryById(id);
        } else return new ResponseEntity("Not Authorize", HttpStatus.UNAUTHORIZED);
    }
}
