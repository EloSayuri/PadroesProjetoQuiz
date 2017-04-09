package hello;

import java.util.List;
import java.util.LinkedList;

public class Activity {

	private String userName;
	private String examTitle;
	private List<Choice> choices = new LinkedList<Choice>();
	private boolean complete;

	public Activity(String userName, String examTitle) {
		this.setUserName(userName);
		this.setExamTitle(examTitle);
		this.setComplete(false);
	}

	// Get-set for the user name
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Get-Set for the exams
	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	// Get-Set for the choices
	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public void addChoice(Choice choice) {
		if (this.getChoices() == null) {
			this.setChoices(new LinkedList<Choice>());
		}
		this.choices.add(choice);
	}

	public void delChoice(Choice choice) {
		for (Choice item : this.getChoices()) {
			if (item.isValid(choice)) {
				this.choices.remove(choice);
			}
		}
	}
	
	public void deleteAllChoices(){
		while (!this.getChoices().isEmpty()) {
	        this.choices.remove(0);
	    }
	}

	// Get-Set to check if the activity is completed
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	// Check if the question is registered in the current activity
	public boolean contains(String questionTitle) {
		if (!(getChoices() == null)) {
			for (Choice choice : getChoices()) {
				if (choice.getQuestionTitle().equals(questionTitle)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
