package com.xoslu.tech.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "categories") @Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks = new ArrayList<>();
    public Category() {}

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setCategory(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setCategory(null);
    }
}
