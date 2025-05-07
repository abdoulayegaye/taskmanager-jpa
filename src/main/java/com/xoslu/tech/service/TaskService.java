package com.xoslu.tech.service;

import com.xoslu.tech.entity.Category;
import com.xoslu.tech.entity.Task;
import com.xoslu.tech.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    Task createTask(String title, String description, LocalDate dueDate);
    Category createCategory(String name, String description);
    User createUser(String username, String email);
    void assignTaskToUser(Task task, User user);
    void assignTaskToCategory(Task task, Category category);
    void markTaskAsCompleted(Task task);
    List<Task> getAllTasks();
    List<Task> getPendingTasks();
    List<Task> getCompletedTasks();
    List<Task> getOverdueTasks();
    List<Task> getTasksByCategory(Category category);
    List<Task> getTasksAssignedToUser(User user);
    Task getTaskById(Long id);
    void updateTask(Task task);
    void deleteTask(Task task);
    Category getCategoryById(Long id);
    User getUserById(Long id);
}
