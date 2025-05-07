package com.xoslu.tech;

import com.xoslu.tech.entity.Category;
import com.xoslu.tech.entity.Task;
import com.xoslu.tech.entity.User;
import com.xoslu.tech.service.TaskService;
import com.xoslu.tech.service.impl.TaskServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskjpa");
        EntityManager em = emf.createEntityManager();

        // Création du service
        TaskService taskService = new TaskServiceImpl(em);

        try {
            // Création de catégories
            Category workCategory = taskService.createCategory("Travail", "Tâches professionnelles");
            Category personalCategory = taskService.createCategory("Personnel", "Tâches personnelles");

            // Création d'utilisateurs
            User john = taskService.createUser("john_doe", "john@example.com");
            User alice = taskService.createUser("alice_smith", "alice@example.com");

            // Création de tâches
            Task task1 = taskService.createTask("Préparer la présentation",
                    "Préparer les slides pour la réunion de demain",
                    LocalDate.now().plusDays(1));
            Task task2 = taskService.createTask("Faire les courses",
                    "Acheter du lait, du pain et des œufs",
                    LocalDate.now().plusDays(2));
            Task task3 = taskService.createTask("Appeler le client",
                    "Discuter des nouveaux besoins du projet",
                    LocalDate.now().minusDays(1));

            // Assignation des tâches à des utilisateurs et catégories
            taskService.assignTaskToUser(task1, john);
            taskService.assignTaskToUser(task2, alice);
            taskService.assignTaskToUser(task3, john);

            taskService.assignTaskToCategory(task1, workCategory);
            taskService.assignTaskToCategory(task2, personalCategory);
            taskService.assignTaskToCategory(task3, workCategory);

            // Marquer une tâche comme terminée
            taskService.markTaskAsCompleted(task3);

            // Affichage des tâches
            System.out.println("--- Toutes les tâches ---");
            List<Task> allTasks = taskService.getAllTasks();
            allTasks.forEach(System.out::println);

            System.out.println("\n--- Tâches en attente ---");
            List<Task> pendingTasks = taskService.getPendingTasks();
            pendingTasks.forEach(System.out::println);

            System.out.println("\n--- Tâches terminées ---");
            List<Task> completedTasks = taskService.getCompletedTasks();
            completedTasks.forEach(System.out::println);

            System.out.println("\n--- Tâches en retard ---");
            List<Task> overdueTasks = taskService.getOverdueTasks();
            overdueTasks.forEach(System.out::println);

            System.out.println("\n--- Tâches par catégorie (Travail) ---");
            List<Task> workTasks = taskService.getTasksByCategory(workCategory);
            workTasks.forEach(System.out::println);

            System.out.println("\n--- Tâches assignées à John ---");
            List<Task> johnTasks = taskService.getTasksAssignedToUser(john);
            johnTasks.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            em.close();
            emf.close();
        }
    }
}