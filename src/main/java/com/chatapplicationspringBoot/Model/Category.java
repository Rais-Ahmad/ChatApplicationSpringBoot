package com.chatapplicationspringBoot.Model;

import javax.persistence.*;

@Entity
@Table(name = "t_category")

public class Category {
    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Description Category POJO class
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId; //Category ID
    @Column(nullable = false)
    private String categoryName;
    @Column(nullable = false)
    private String categoryDate;
    private boolean status;


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

    public String getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(String categoryDate) {
        this.categoryDate = categoryDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
