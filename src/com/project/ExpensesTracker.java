package com.project;


import java.util.ArrayList;
import java.util.Scanner;

class Expense 
{
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) 
    {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() 
    {
        return description;
    }

    public double getAmount() 
    {
        return amount;
    }

    public String getCategory() 
    {
        return category;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: " + amount + ", Category: " + category;
    }
}

public class ExpensesTracker {

    private static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
       
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewAllExpenses();
                    break;
                case 3:
                    viewExpensesByCategory();
                    break;
                case 4:
                    calculateTotalExpenses();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Thank you for using the Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    // Method to add an expense
    private static void addExpense() 
    {
        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 

        System.out.print("Select category: ");
        String category =dispCategory();
        
        expenses.add(new Expense(description, amount, category));
        System.out.println("Expense added successfully!");
    }
    public static String dispCategory()
    {
    	System.out.println("1.Living Costs\n2.Food & Groceries\n3.Transportation\n4.Healthcare\n5.Others");
    	int sel=sc.nextInt();
    	switch(sel)
    	{
	    	case 1: return "Living Costs";
	    	case 2: return "Food & Groceries";
	    	case 3: return "Transportation";
	    	case 4: return "Healthcare";
	    	case 5: return "Others";
	    	default: System.out.println("Select category:");
	    			 return	dispCategory();
    	}
    }
    // Method to display all expenses
    private static void viewAllExpenses() 
    {
        if (expenses.isEmpty()) 
        {
            System.out.println("No expenses recorded.");
        } 
        else 
        {
            System.out.println("\nAll Expenses:");
            for (Expense expense : expenses) 
            {
                System.out.println(expense);
            }
        }
    }

    // Method to display expenses filtered by category
    private static void viewExpensesByCategory() 
    {
        System.out.print("Enter category to filter: ");
//        String category = sc.nextLine();
        String category = dispCategory();
        

        System.out.println("\nExpenses in category: " + category);
        boolean hasExpenses = false;

        for (Expense expense : expenses) 
        {
            if (expense.getCategory().equalsIgnoreCase(category)) 
            {
                System.out.println(expense);
                hasExpenses = true;
            }
        }

        if (!hasExpenses) 
        {
            System.out.println("No expenses found in this category.");
        }
    }

    // Method to calculate and display total expenses
    private static void calculateTotalExpenses() 
    {
        double total = 0;

        for (Expense expense : expenses) 
        {
            total += expense.getAmount();
        }
        System.out.println("\nTotal Expenses: " + total);
    }
}