package com.chatapplicationspringBoot.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //User ID
    @Column(nullable = false)
    private String firstName; //User First Name
    private String lastName;//User Last Name
    @Column(nullable = false, unique = true)
    private String email;//User email
    private int age;//User age

    @Column(nullable = false)
    private String password; //User Password

    @OneToMany(targetEntity = Chat.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Chat> chat = new ArrayList<>();


    /**
     * Many-Many Relationship
     */

    @ManyToMany(targetEntity = Category.class, cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "t_UserCategory",
            joinColumns = {
                    @JoinColumn(name = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "categoryId")
            }
    )
    //private List<Category> category = new ArrayList<>();
            Set<Category> category = new HashSet<Category>();

    public User() {
        super();
    }

    public User(long id, String firstName, String lastName, String email, int age, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    // Getter and setter functions for User class

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return lastName;
    }

    public void setSecondName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
