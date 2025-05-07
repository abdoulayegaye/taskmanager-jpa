package com.xoslu.tech.repository;

import com.xoslu.tech.entity.Category;
import com.xoslu.tech.entity.Task;
import com.xoslu.tech.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class TaskRepository {

    private final EntityManager entityManager;

    public TaskRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }

    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    public List<Task> findAll() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    public List<Task> findByCompletedVersion1(boolean completed) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.isCompleted = :completed", Task.class);
        query.setParameter("completed", completed);
        return query.getResultList();
    }

    public List<Task> findByCompletedVersion2(boolean completed) {
        return entityManager
                .createQuery("SELECT t FROM Task t WHERE t.isCompleted = :completed", Task.class)
                .setParameter("completed", completed)
                .getResultList();
    }

    public List<Task> findByCompletedVersion3(boolean completed) {
        return entityManager
                .createNamedQuery("toto", Task.class)
                .setParameter("completed", completed)
                .getResultList();
    }

    public List<Task> findByDueDateBefore(LocalDate date) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.dueDate < :date", Task.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public void update(Task task) {
        entityManager.getTransaction().begin();
        entityManager.merge(task);
        entityManager.getTransaction().commit();
    }

    public void delete(Task task) {
        entityManager.getTransaction().begin();
        entityManager.remove(task);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Long id) {
        Task task = findById(id);
        if (task != null) {
            delete(task);
        }
    }

    public List<Task> findByCategory(Category category) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.category = :category", Task.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<Task> findByAssignedUser(User user) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.user = :user", Task.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
}
