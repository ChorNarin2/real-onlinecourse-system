package com.example.demo.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Table;


@Entity
@Table(name = "course")
@Data
@RequiredArgsConstructor
public class Course {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //========================================>Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    
    @Column()
    private String title;
    
    @Column()
    private String description;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at	;


    // =========================================> Relationship Many to One

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)   
    private Category category;

    // =========================================> Relationship One to Many

    @JsonManagedReference
    @JsonIgnore // Skip this field during serialization
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> video;

    @JsonManagedReference
    @JsonIgnore // Skip this field during serialization
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;

    @JsonManagedReference
    @JsonIgnore 
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

}
