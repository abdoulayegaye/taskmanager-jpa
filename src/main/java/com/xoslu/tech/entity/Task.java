package com.xoslu.tech.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
}
