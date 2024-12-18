package com.example.demo.models;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.models.Enum.VideoEnumStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "videos")
@Data
@RequiredArgsConstructor
public class Video {

    // =====================================================> Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "file_url", nullable = false)
    private String file_url;

    @Enumerated(EnumType.STRING)
    @Column
    private VideoEnumStatus status; // Progress or Active

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date create_at;

    // ======================================> Many to One
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

     // Add this field to explicitly reference the User associated with the video
     @JsonBackReference
     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user;


}
