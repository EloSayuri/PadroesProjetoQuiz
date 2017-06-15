package hello;

import static spark.Spark.*;

public class MainServer {

	final static Model model = new Model();

	public static void main(String[] args) {

		// Get port config of heroku on environment variable
		ProcessBuilder process = new ProcessBuilder();
		Integer port;
		if (process.environment().get("PORT") != null) {
			port = Integer.parseInt(process.environment().get("PORT"));
		} else {
			port = 8080;
		}
		
		port(port);
		
		initializeModel();
		
		staticFileLocation("/static");
		
		REST controller = new REST(model);
		controller.getLogin();
		controller.getExams();
		controller.getExam();
		controller.getNextQuestion();
		controller.setUserAnswer();
		controller.getActivityResults();
		controller.checkActivity();
		controller.resetActivity();
		controller.createUser();
		controller.getEx();
		controller.getLoginAndroid();
	}
	
	public static void initializeModel() {
		
		// Add initial users
		model.addUser(new User("Jhon", true, new Login("jhon@gmail.com", "123")));
		model.addUser(new User("Leonardo", true, new Login("lcp.leonardo@outlook.com", "123")));
		model.addUser(new User("Gustavo", false, new Login("luisaraki@hotmail.com", "123")));
		model.addUser(new User("William", true, new Login("wifosilv@gmail.com", "123")));
		model.addUser(new User("Robson", true, new Login("robsoncartes@gmail.com", "123")));
		model.addUser(new User("Igor", true, new Login("igor.checan@gmail.com", "123")));
		
		// Add exams and questions
		Exam newExam;
		Question newQuestion;
		
		newExam = new Exam("Database Test", "Technology", "Database questions to improve your knowledge", "Nicholas", "imagem01");
		
		newQuestion = new Question("You can add a row using SQL in a database with which of the following?");
		newQuestion.addAnswer(new Answer("ADD", false));
		newQuestion.addAnswer(new Answer("CREATE", false));
		newQuestion.addAnswer(new Answer("INSERT", true));
		newQuestion.addAnswer(new Answer("MAKE", false));
		newExam.addQuestion(newQuestion);
		
		newQuestion = new Question("The SQL WHERE clause:");
		newQuestion.addAnswer(new Answer("limits the column data that are returned.", false));
		newQuestion.addAnswer(new Answer("limits the row data are returned.", true));
		newQuestion.addAnswer(new Answer("Both A and B are correct.", false));
		newQuestion.addAnswer(new Answer("Neither A nor B are correct.", false));
		newExam.addQuestion(newQuestion);
		
		newQuestion = new Question("Which of the following is the original purpose of SQL?");
		newQuestion.addAnswer(new Answer("To specify the syntax and semantics of SQL data definition language", false));
		newQuestion.addAnswer(new Answer("To specify the syntax and semantics of SQL manipulation language", false));
		newQuestion.addAnswer(new Answer("To define the data structures", false));
		newQuestion.addAnswer(new Answer("All of the above.", true));
		newExam.addQuestion(newQuestion);
		
		model.addExam(newExam);
		
		newExam = new Exam("General Knowledge", "General", "Some wikipedia questions for you to enjoy!", "Gustavo", "imagem02");
		
		newQuestion = new Question("If a=1 and b=2, what is a+b?");
		newQuestion.addAnswer(new Answer("12", false));
		newQuestion.addAnswer(new Answer("3", true));
		newQuestion.addAnswer(new Answer("4", false));
		newQuestion.addAnswer(new Answer("10", false));
		newExam.addQuestion(newQuestion);
		
		newQuestion = new Question("The IT capital of India is:");
		newQuestion.addAnswer(new Answer("Bangalore", true));
		newQuestion.addAnswer(new Answer("Mumbai", false));
		newQuestion.addAnswer(new Answer("Mexico", false));
		newQuestion.addAnswer(new Answer("Hyderabad", false));
		newExam.addQuestion(newQuestion);
		
		model.addExam(newExam);
		
		// Robson Exam
		
		newExam = new Exam("Advanced PLSQL", "General PL/SQL", "Other practical questions", "Robson", "imagem03");
		
		newQuestion = new Question("How many types of PL/SQL loops are exists?");
		newQuestion.addAnswer(new Answer("3", true));
		newQuestion.addAnswer(new Answer("4", false));
		newQuestion.addAnswer(new Answer("2", false));
		newQuestion.addAnswer(new Answer("1", false));
		newExam.addQuestion(newQuestion);
		
		newQuestion = new Question("");
		
		model.addExam(newExam);
	}
}
