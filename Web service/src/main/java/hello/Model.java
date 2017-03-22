package hello;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model {

	ObjectContainer users = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/users.db4o");
	ObjectContainer exams = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/exams.db4o");
	ObjectContainer activities = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/activities.db4o");

	// Insert user in the database
	/*
	public boolean addUser(User user) {
		if (isUserAvailable(user.getName())) {
			users.store(user);
			users.commit();
			return true;
		} else {
			return false;
		}
	}*/
	
	public boolean addUser(User user) {
		if (isUserAvailable(user.getName())) {
			users.store(user);
			users.commit();
			return true;
		} else {
			return false;
		}
	}

	// Remove user from the database
	public void delUser(String username) {
		Query query = users.query();
		query.constrain(User.class);
		List<User> allUsers = query.execute();
		for (User user : allUsers) {
			if (user.getName().equals(username)) {
				users.delete(user);
				users.commit();
				break;
			}
		}
	}

	// Check if the user has already been registered
	public boolean isUserAvailable(String username) {
		Query query = users.query();
		query.constrain(User.class);
		ObjectSet<User> allUsers = query.execute();
		
		for (User user : allUsers) {
			if (user.getName().equals(username)) {
				return false;
			}
		}
		
		return true;
	}

	// Insert exam in the database
	public boolean addExam(Exam exam) {
		if (isExamAvailable(exam.getTitle())) {
			exams.store(exam);
			exams.commit();
			return true;
		} else {
			return false;
		}
	}

	// Remove exam from the database
	public void delExam(String examTitle) {
		Query query = exams.query();
		query.constrain(Exam.class);
		List<Exam> allExams = query.execute();
		for (Exam exam : allExams) {
			if (exam.getTitle().equals(examTitle)) {
				exams.delete(exam);
				exams.commit();
				break;
			}
		}
	}

	// Check if the exam has already been registered
	public boolean isExamAvailable(String examTitle) {
		Query query = exams.query();
		query.constrain(Exam.class);
		ObjectSet<Exam> allExams = query.execute();
		for (Exam exam : allExams) {
			if (exam.getTitle().equals(examTitle)) {
				return false;
			}
		}
		
		return true;
	}

	// Insert activity in the database
	public boolean addActivity(Activity activity) {
		if (isActivityAvailable(activity.getUserName(), activity.getExamTitle())) {
			activities.store(activity);
			activities.commit();
			return true;
		} else {
			return false;
		}
	}

	// Remove activity from the database
	public void delActivity(String userName, String examTitle) {
		Query query = activities.query();
		query.constrain(Activity.class);
		List<Activity> allActivities = query.execute();
		for (Activity activity : allActivities) {
			if ((activity.getUserName().equals(userName)) && (activity.getExamTitle().equals(examTitle))) {
				activities.delete(activity);
				activities.commit();
				break;
			}
		}
	}

	// Check if the activity has already been registered
	public boolean isActivityAvailable(String userName, String examTitle) {
		Query query = activities.query();
		query.constrain(Activity.class);
		ObjectSet<Activity> allActivities = query.execute();
		for (Activity activity : allActivities) {
			if ((activity.getUserName().equals(userName)) && (activity.getExamTitle().equals(examTitle))) {
				return false;
			}
		}
		return true;
	}

	// Get all registered activities
	public List<Activity> getActivities() {
		Query query = activities.query();
		query.constrain(Activity.class);
		ObjectSet<Activity> allActivities = query.execute();
		return allActivities;
	}

	// Get an exam by his title
	public Activity getActivity(String userName, String examTitle) {
		List<Activity> allActivities = this.getActivities();
		for (Activity activity : allActivities) {
			if ((activity.getUserName().equals(userName)) && (activity.getExamTitle().equals(examTitle))) {
				return activity;
			}
		}
		return null;
	}

	// Check login data and return the user information
	public User login(String email, String password) {
		Query query = users.query();
		query.constrain(User.class);
		ObjectSet<User> allUsers = query.execute();
		for (User user : allUsers) {
			if (user.getLogin().isValid(new Login(email, password))) {
				return user;
			}
		}
		return null;
	}

	// Get all registered exams
	public List<Exam> getExams() {
		Query query = exams.query();
		query.constrain(Exam.class);
		ObjectSet<Exam> allExams = query.execute();
		return allExams;
	}

	// Get an exam by his title
	public Exam getExam(String examTitle) {
		List<Exam> allExams = this.getExams();
		for (Exam exam : allExams) {
			if (exam.getTitle().equals(examTitle)) {
				return exam;
			}
		}
		return null;
	}

	// Get next question to be displayed in the user exam
	public Question getNextQuestion(String userName, String examTitle) {
		Exam exam = getExam(examTitle);
		Activity activity = getActivity(userName, examTitle); 
		if(activity == null){
			activity = new Activity(userName, examTitle);
			addActivity(activity);
		}
		
		for(Question question : exam.getQuestions()){
			if(!activity.contains(question.getQuestion())){
				return question;
			}
		}
		
		// Activity completed
		activity.setComplete(true);
		activities.store(activity);
		activities.commit();
		return null;
	}
	
	// Check if the user answer is correct
	public boolean isCorrectChoice(String examTitle, String questionTitle, String userAnswer){
		Exam exam = getExam(examTitle);
		Question question = exam.getQuestion(questionTitle);
		for(Answer answer : question.getAnswers()){
			if(answer.getAnswer().equals(userAnswer)){
				if(answer.isCorrect()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Set if the users answer is correct or not
	public boolean setAnswer(String userName, String examTitle, String questionTitle, String userAnswer){
		boolean correctAnswer = false;
		Activity activity = getActivity(userName, examTitle);
		if(isCorrectChoice(examTitle, questionTitle, userAnswer)){
			correctAnswer = true;
		}
		activity.addChoice(new Choice(questionTitle, userAnswer, correctAnswer));
		//to update an array in DB4O you must reference it explicitly 
		// -> Object.List -> if you reference only the Object like you do with others attributes it does not work 
		activities.store(activity.getChoices());
		activities.commit();
		return correctAnswer;
	}
	
	// Check if the activity has already been completed
	public boolean getActivityStatus(String userName, String examTitle){
		Activity activity = getActivity(userName, examTitle);
		if(activity != null){
			if(activity.isComplete()){
				return true;
			}
		}
		return false;
	}
	
	public float getActivityResults(String userName, String examTitle){
		int questions = 0, corrects = 0;
		Activity activity = getActivity(userName, examTitle);
		for(Choice choice : activity.getChoices()){
			questions++;
			if(choice.isCorrect()){
				corrects++;
			}
		}
		
		return (corrects * 100) / questions;
	}
	
	public boolean resetActivity(String userName, String examTitle){
		Activity activity = getActivity(userName, examTitle);
		activity.setComplete(false);
		activity.deleteAllChoices();
		activities.store(activity.getChoices());
		activities.store(activity);
		activities.commit();
		return true;
	}
}
