package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "discount")
@Data
@RequiredArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private BigDecimal discountPercent;

    @Column
    @CreationTimestamp
    private Date startDate;

    @Column
    @CreationTimestamp
    private LocalDateTime endDate;

}
