import java.util.*;
import java.util.Scanner;
import java.util.Timer;  
import java.util.TimerTask;

public class OnlineExamSystem {
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int timeRemaining;
	private int questionCount;
	private String[] questions;
	private String[] option1;
	private String[] option2;
	private String[] option3;
	private String[] option4;
	private int[] userAnswers;
	private int[] correctAnswers;
	Timer timer = new Timer();  

	public OnlineExamSystem(String username, String password) {
		this.username = username;
		this.password = password;
		System.out.println("Sucessfully You are registered! ");
		this.isLoggedIn = false;
		this.timeRemaining = 10; // in minutes
		this.questionCount = 10;
		this.userAnswers = new int[questionCount];
		// initialize questions

		this.questions=new String[]{"Which of the following is not a Java features?",
					"____ is used to find and fix bugs in the Java programs.",
					" An interface with no fields or methods is known as a ______.",
					" What is the return type of the hashCode() method in the Object class?",
					"Which of the following is a valid long literal?",
					"What does the expression float a = 35 / 0 return?",
					"Evaluate the following Java expression, if x=3, y=5, and z=10:   ++z + y - y + z + x++",
					"Which of the following tool is used to generate API documentation in HTML format from doc comments in source code?",
					"Which of the following creates a List of 3 visible items and multiple selections abled?",
					"Which of the following for loop declaration is not valid?"
					};
		// initialize option1
		this.option1=new String[]{
			"Dynamic",
			"JVM",
			"Runnable Interface",
			"Object",
			"ABH8097",
			"0",
			"24",
			"javap tool",
			"new List(false, 3)",
			"for ( int i = 99; i >= 0; i / 9 )"
		};
		// initialize option2
		this.option2=new String[]{
			"Architecture Neutral",
			"JRE",
			"Marker Interface",
			"int",
			"L990023",
			"Not a Number",
			"23",
			"javaw command",
			"new List(3, true)",
			"for ( int i = 7; i <= 77; i += 7 )"
		};
		// initialize option3
		this.option3=new String[]{
			"Use of pointers",
			"JDK",
			"Abstract Interface",
			"long",
			"904423",
			"nfinity",
			"20",
			"Javadoc tool",
			"new List(true, 3)",
			"for ( int i = 20; i >= 2; - -i )"
		};
		// initialize option4
		this.option4=new String[]{
			"Object-oriented",
			"JDB",
			"CharSequence Interface",
			"void",
			"0xnf029L",
			"Run time exception",
			"25",
			"javah command",
			"new List(3, false)",
			"for ( int i = 2; i <= 20; i = 2* i )"
		};

		// initialize correct answers
		this.correctAnswers=new int[]{3,4,2,2,4,3,4,3,2,1};
	}

	public void login() {
		System.out.println("Log in to give the Exam ");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username: ");
		String inputUsername = scanner.nextLine();
		System.out.print("Password: ");
		String inputPassword = scanner.nextLine();
		if (inputUsername.equals(username) && inputPassword.equals(password)) {
			isLoggedIn = true;
			System.out.println("Login successful Best of Luck Dear");
			System.out.println("Update your profile: ");
			System.out.print("Enter your Name:");
            String profileUpdateName = scanner.nextLine();
            System.out.print("Enter your email:");
            String profileUpdateEmail = scanner.nextLine();
		} else {
			System.out.println("Login failed. Please try again.");
		}
	}

	public void logout() {
		isLoggedIn = false;
		System.out.println("Logout successful.");
	}

	public void startExam() {
		if (!isLoggedIn) {
			System.out.println("Please login first.");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
		
		//schedule the task or timmer on
		timer.schedule(new SubmitExam(), timeRemaining*10*1000); 
		
		for (int i = 0; i < questionCount; i++) {
			System.out.println("Question " + (i + 1) + ":" + questions[i]);
			System.out.println("1. "+ option1[i]);
			System.out.println("2. "+ option2[i]);
			System.out.println("3. "+ option3[i]);
			System.out.println("4. "+ option4[i]);
			System.out.print("Your answer (1 or 2 or 3 or 4): ");
			int answer = scanner.nextInt();
			userAnswers[i] = answer;
		}

		System.out.println("Would you like to submit? \n1:Yes \n2:NO ");
		int n = scanner.nextInt();
		if (n == 1) {
				timer.schedule(new SubmitExam(), 1); 
			
		} else {
			// auto-submit exam when time is up
				System.out.println("Wait for Closing session.....\nAnswers are submitted automatically after timeup!!");
		}

	}
	
    class SubmitExam extends TimerTask{
        public void run() {
			if (!isLoggedIn) {
				System.out.println("Please login first.");
				return;
			}
			System.out.println("Your Answers are Submited Successfully!!\nWait for Results......");
			int score = 0;
			for (int i = 0; i < questionCount; i++) {
				if (userAnswers[i] == correctAnswers[i]) {
					score++;
				}
			}
			System.out.println("Your score is " + score + " out of " + questionCount + ".");
			System.out.println("Best of luck!!");
			timer.cancel();
			logout();
		}
    }
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------Enter your username and password to Register------------------");
		System.out.print("User name:");
		String uName = sc.nextLine();
		System.out.print("Password:");
		String pWord = sc.nextLine();
		OnlineExamSystem examSystem = new OnlineExamSystem(uName, pWord);
		examSystem.login();
		examSystem.startExam();
	}
}
