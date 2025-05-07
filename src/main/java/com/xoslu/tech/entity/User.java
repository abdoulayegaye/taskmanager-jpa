package com.xoslu.tech.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void assignTask(Task task) {
        tasks.add(task);
        task.setUser(this);
    }
}
