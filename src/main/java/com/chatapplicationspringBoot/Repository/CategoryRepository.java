package com.chatapplicationspringBoot.Repository;

import com.chatapplicationspringBoot.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByStatus(boolean status);
}
