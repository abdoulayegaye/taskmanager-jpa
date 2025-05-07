package com.xoslu.tech.service.impl;

import com.xoslu.tech.entity.Category;
import com.xoslu.tech.entity.Task;
import com.xoslu.tech.entity.User;
import com.xoslu.tech.repository.TaskRepository;
import com.xoslu.tech.service.TaskService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EntityManager entityManager;

    public TaskServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.taskRepository = new TaskRepository(entityManager);
    }

    @Override
    public Task createTask(String title, String description, LocalDate dueDate) {
        Task task = new Task(title, description, dueDate);
        taskRepository.save(task);
        return task;
    }

    @Override
    public void assignTaskToUser(Task task, User user) {
        user.assignTask(task);
        taskRepository.update(task);
    }

    @Override
    public void assignTaskToCategory(Task task, Category category) {
        task.setCompleted(true);
        taskRepository.update(task);
    }

    @Override
    public void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
        taskRepository.update(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getPendingTasks() {
        return taskRepository.findByCompletedVersion1(false);
    }

    @Override
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedVersion2(true);
    }

    @Override
    public List<Task> getOverdueTasks() {
        return taskRepository.findByCompletedVersion3(false);
    }

    @Override
    public List<Task> getTasksByCategory(Category category) {
        return taskRepository.findByCategory(category);
    }

    @Override
    public List<Task> getTasksAssignedToUser(User user) {
        return taskRepository.findByAssignedUser(user);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Category createCategory(String name, String description) {
        Category category = new Category(name, description);
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        return category;
    }

    @Override
    public User createUser(String username, String email) {
        User user = new User(username, email);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public Category getCategoryById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
