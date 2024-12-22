package com.project;

import java.util.*;

class Task {
    private String description;
    private String priority;
    private boolean isCompleted;

    public Task(String description, String priority) {
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Task: " + description + " | Priority: " + priority + " | Status: " +
                (isCompleted ? "Completed" : "Pending");
    }
}

public class ToDoList {
    private List<Task> tasks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority (High/Medium/Low): ");
        String priority = scanner.nextLine();

        Task newTask = new Task(description, priority);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    public void viewTasks(boolean showCompleted) {
        System.out.println(showCompleted ? "\nCompleted Tasks:" : "\nPending Tasks:");
        boolean found = false;

        for (Task task : tasks) {
            if (task.isCompleted() == showCompleted) {
                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No " + (showCompleted ? "completed" : "pending") + " tasks found.");
        }
    }

    public void markTaskAsCompleted() {
        System.out.print("Enter task description to mark as completed: ");
        String description = scanner.nextLine();

        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description) && !task.isCompleted()) {
                task.markAsCompleted();
                System.out.println("Task marked as completed!");
                return;
            }
        }

        System.out.println("Task not found or already completed.");
    }

    public void deleteTask() {
        System.out.print("Enter task description to delete: ");
        String description = scanner.nextLine();

        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getDescription().equalsIgnoreCase(description)) {
                iterator.remove();
                System.out.println("Task deleted successfully!");
                return;
            }
        }

        System.out.println("Task not found.");
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Pending Tasks");
            System.out.println("3. View Completed Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks(false);
                case 3 -> viewTasks(true);
                case 4 -> markTaskAsCompleted();
                case 5 -> deleteTask();
                case 6 -> {
                    System.out.println("Exiting To-Do List application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.showMenu();
    }
}

