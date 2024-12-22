package com.project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Questions{
	String questionText;
	String options[];
	int correctOption;
	Questions(String questionText,String options[],int correctOption){
		this.questionText=questionText;
		this.options=options;
		this.correctOption=correctOption;
	}
	public static Questions parseQuestion(String csv) {
		String ar[]=csv.split(",");
		if(ar.length!=3) throw new IllegalArgumentException("The CSV is not properly Formatted");
		String questionText=ar[0];
		String options[]=ar[1].split("\\|");
	    int correctAns=0;
		try {
			correctAns=Integer.parseInt(ar[2]);
		}catch(NumberFormatException e) {
			System.out.println("Correct option is not in the Number format");
		}
		return new Questions(questionText,options,correctAns);
	}
	public String toCSV() {
		return questionText+","+String.join("|",options)+","+correctOption;
	}
	public void displayQuestion(int qn) {
		System.out.println("Question No:"+qn+" "+questionText);
		for(int i=0;i<options.length;i++) {
			System.out.println(i+1+"."+options[i]);
		}
	}
	public boolean isCorrect(int op) {
		return correctOption==op;
	}
}
public class Test2 {
	private static final String QUESTION_FILE="question_bank.csv"; 
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Questions> questionBank=loadQuestions(); 
    public static ArrayList<Questions> loadQuestions() {
    	ArrayList<Questions> questions=new ArrayList<>();
      	try(BufferedReader br=new BufferedReader(new FileReader(QUESTION_FILE))){
      		String line;
      		while((line=br.readLine())!=null) {
      		  questions.add(Questions.parseQuestion(line));
      		}
      	}catch(IOException e) {
      		e.printStackTrace();
      	}
      	return questions;
    }
    public static void saveQuestions() {
    	try(BufferedWriter bw=new BufferedWriter(new FileWriter(QUESTION_FILE))){
    		for(Questions question:questionBank) {
    			bw.write(question.toCSV());
    			bw.newLine();
    		}
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    public static void addQuestion() {
    	System.out.println("Enter Question Text:");
    	String questionText=sc.nextLine();
    	System.out.println("Enter options by sparated with , ");
    	String options=sc.nextLine();
    	System.out.println("Enter Correct Option:");
    	int op=sc.nextInt();
    	sc.nextLine();
    	questionBank.add(new Questions(questionText,options.split(","),op));
//    	saveQuestions();
    	System.out.println("Question added Succussfully");
    }
    public static void conductQuiz() {
    	if(questionBank==null) {
    		System.out.println("Please Add Questions first");
    		return;
    	}
    	System.out.println("Enter number of questions to solve:");
        int op=sc.nextInt();
        if(op>questionBank.size()) {
        	System.out.println("Insuffient Questions..");
        	return;
        }
        int score=0;
        for(int i=0;i<op;i++) {
 		   Questions question=questionBank.get(i);
 		   question.displayQuestion(i+1);
 		   System.out.println("Enter Correct Option:");
 		   int cop=sc.nextInt();
 		   if(question.isCorrect(cop))score++;
 	   }
       System.out.println("Your Final Score="+score+"/"+op);
    }
    public static void updateQuestion() {
        if (questionBank.isEmpty()) {
            System.out.println("No questions available to update.");
            return;
        }

        System.out.println("Select the question number to update:");
        for (int i = 0; i < questionBank.size(); i++) {
            System.out.println((i + 1) + ". " + questionBank.get(i).questionText);
        }

        int questionIndex = sc.nextInt() - 1;
        sc.nextLine(); 

        if (questionIndex < 0 || questionIndex >= questionBank.size()) {
            System.out.println("Invalid question number!");
            return;
        }

        Questions questionToUpdate = questionBank.get(questionIndex);

        System.out.println("Current question: " + questionToUpdate.questionText);
        System.out.println("Enter new question text (or press Enter to keep current):");
        String newQuestionText = sc.nextLine();
        if (!newQuestionText.isEmpty()) {
            questionToUpdate.questionText = newQuestionText;
        }
        System.out.println("Current options: " + String.join(", ", questionToUpdate.options));
        System.out.println("Enter new options separated by commas (or press Enter to keep current):");
        String newOptions = sc.nextLine();
        if (!newOptions.isEmpty()) {
            questionToUpdate.options = newOptions.split(",");
        }
        System.out.println("Current correct option: " + questionToUpdate.correctOption);
        System.out.println("Enter new correct option (or press Enter to keep current):");
        String newCorrectOption = sc.nextLine();
        if (!newCorrectOption.isEmpty()) {
            try {
                questionToUpdate.correctOption = Integer.parseInt(newCorrectOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for correct option. No changes made.");
            }
        }
        saveQuestions();
        System.out.println("Question updated successfully!");
    }
    public static void deleteQuestion() {
        if (questionBank.isEmpty()) {
            System.out.println("No questions available to delete.");
            return;
        }
        System.out.println("Select the question number to delete:");
        for (int i = 0; i < questionBank.size(); i++) {
            System.out.println((i + 1) + ". " + questionBank.get(i).questionText);
        }

        int questionIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (questionIndex < 0 || questionIndex >= questionBank.size()) {
            System.out.println("Invalid question number!");
            return;
        }

        System.out.println("Are you sure you want to delete this question? (yes/no)");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            questionBank.remove(questionIndex);
            saveQuestions();
            System.out.println("Question deleted successfully!");
        } else {
            System.out.println("Deletion canceled.");
        }
    }


	public static void main(String[] args){
	 boolean isExit=false;
	 while(!isExit) {
		 System.out.println("1.Add Question\n2.Conduct Quiz\n3.Save questions\n4.Upadte questions\n5.Delete question\n6.Exit");
		 int op=sc.nextInt();
		 sc.nextLine();
		 switch(op) {
		 	case 1:
	            addQuestion();
	            break;
	        case 2:
	            conductQuiz();
	            break;
	        case 3:
	            saveQuestions();
	            break;
	        case 4:
	            updateQuestion();
	            break;
	        case 5:
	            deleteQuestion(); // New option
	            break;
	        case 6:
	            isExit = true;
	            break;
	        default:
	            System.out.println("Invalid option! Try again.");
		 }
	 }
	}
}