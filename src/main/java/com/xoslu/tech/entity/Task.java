package com.xoslu.tech.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NamedQuery(name = "toto", query = "SELECT t FROM Task t WHERE t.isCompleted = :completed")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed", columnDefinition = "bit not null default 0")
    private boolean isCompleted;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Task() {}

    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }
}
