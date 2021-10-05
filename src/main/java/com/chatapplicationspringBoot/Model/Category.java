package com.chatapplicationspringBoot.Model;

import javax.persistence.*;

@Entity
@Table(name = "t_category")

public class Category {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId; //Category ID
    @Column(nullable = false)
    private String categoryName;

    public Category() {
        super();
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
