package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Column
    private boolean watched;

    @Column
    @CreationTimestamp
    private Date lastWatched;

}
