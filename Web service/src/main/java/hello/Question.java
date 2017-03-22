package hello;

import java.util.List;
import java.util.LinkedList;

public class Question {
	
	private String question;
	private String assertion;
	private List<Answer> answers = new LinkedList<Answer>();
	
	public Question(String question){
		this.setQuestion(question);
	}
	
	// Get-Set for the question title
	public String getQuestion() {
		return this.question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// Get-Set for the user assertion
	public String getAssertion() {
		return this.assertion;
	}
	
	public void setAssertion(String assertion) {
		// Only allow informations registered at the answers attribute
		for(Answer answer : this.getAnswers()){
			if(answer.getAnswer().equals(assertion)){
				this.assertion = assertion;
			}
		}
	}
	
	// Get-Set for answers
	public List<Answer> getAnswers(){
		return this.answers;
	}
	public void setAnswers(List<Answer> answers){
		this.answers = answers;
	}

	// Add question for the exam
	public void addAnswer(Answer answer){
		this.answers.add(answer);
	}
	
	// Remove answer from the list of answers of the question
	public void delAnswer(Answer answer){
		for(Answer item: this.getAnswers()){
			if(item.isValid(answer)){
				this.answers.remove(answer);
			}
		}
	}
	
	// Check compatibility between two questions
	public boolean isValid(String questionTitle){
		return this.getQuestion().equals(questionTitle) ? true : false;
	}
}
