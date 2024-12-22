package com.project;

import java.util.Scanner;

public class UnitConverter 
{
	static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("Welcome to the Unit Converter!");
            System.out.println("Please select a conversion type:");
            System.out.println("1. Length");
            System.out.println("2. Weight");
            System.out.println("3. Temperature");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int opt = sc.nextInt();
            switch (opt) 
            {
                case 1: convertLength();
                    	break;
                case 2:	convertWeight();
                    	break;
                case 3:	convertTemperature();
                    	break;
                case 4:	isExit = true;
                       	System.out.println("Thank you for using the Unit Converter!");
                       	break;
                default:System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void convertLength() 
    {
        System.out.println("You selected Length Conversion.");
        System.out.println("Choose the units:");
        System.out.println("1. Meters to Kilometers");
        System.out.println("2. Kilometers to Meters");
        System.out.print("Enter your choice: ");
        int opt = sc.nextInt();
        System.out.print("Enter the value: ");
        double value = sc.nextDouble();
        double result;

        switch (opt)
        {
            case 1:	result = value / 1000;
		            System.out.println("The value in Kilometers is: " + result);
		            break;
            case 2:	result = value * 1000;
		            System.out.println("The value in Meters is: " + result);
		            break;
            default:System.out.println("Invalid choice.....");
        }
    }

    public static void convertWeight() 
    {
        System.out.println("You selected Weight Conversion.");
        System.out.println("Choose the units:");
        System.out.println("1. Kilograms to Grams");
        System.out.println("2. Grams to Kilograms");
        System.out.print("Enter your choice: ");
        int opt = sc.nextInt();
        System.out.print("Enter the value: ");
        double value = sc.nextDouble();
        double result;

        switch (opt) 
        {
            case 1:	result = value * 1000; 
	                System.out.println("The value in Grams is: " + result);
	                break;
            case 2:	result = value / 1000; 
	                System.out.println("The value in Kilograms is: " + result);
	                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }


    public static void convertTemperature() 
    {
        System.out.println("You selected Temperature Conversion.");
        System.out.println("Choose the units:");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Enter your choice: ");
        int opt = sc.nextInt();
        System.out.print("Enter the value: ");
        double value = sc.nextDouble();
        double result;

        switch (opt)
        {
            case 1: result = (value * 9 / 5) + 32; 
	                System.out.println("The value in Fahrenheit is: " + result);
	                break;
            case 2: result = (value - 32) * 5 / 9; 
	                System.out.println("The value in Celsius is: " + result);
	                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

}