package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    private String description;

    // Scores from 1 to 10
    @Setter
    private int urgency;
    @Setter
    private int importance;

    // Calculated field: DO_FIRST, SCHEDULE, DELEGATE, ELIMINATE

    private String quadrant;
    private LocalDateTime createdAt;

    @PrePersist
    @PreUpdate
    private void calculateQuadrant() {
        this.createdAt = LocalDateTime.now();

        if (urgency >= 5 && importance >= 5) {
            this.quadrant = "DO_FIRST";
        } else if (urgency < 5 && importance >= 5) {
            this.quadrant = "SCHEDULE";
        } else if (urgency >= 5) {
            this.quadrant = "DELEGATE";
        } else {
            this.quadrant = "ELIMINATE";
        }
    }

    public Long getId() {
        return id;
    }

    public Tasks(String title, String description, int urgency, int importance) {
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.importance = importance;

    }

    public Tasks() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getUrgency() {
        return urgency;
    }

    public int getImportance() {
        return importance;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}