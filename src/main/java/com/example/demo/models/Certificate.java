package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "certificates")
@Data
@RequiredArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "issue_date")
    @CreationTimestamp
    private Date issueDate;

    @Column
    private String certificateUrl;

}
