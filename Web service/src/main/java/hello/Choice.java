package hello;

public class Choice {
	
	private String questionTitle;
	private String answer;
	private boolean correct;
	
	public Choice(String questionTitle, String answer, boolean correct){
		this.setQuestionTitle(questionTitle);
		this.setAnswer(answer);
		this.setCorrect(correct);
	}
	
	// Get-Set for the question title
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	// Get-Set for the answer
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	// Get-Set to check if the users choice is correct
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean isValid(Choice choice){
		return this.getQuestionTitle().equals(choice.getQuestionTitle()) && 
				this.getAnswer().equals(choice.getAnswer()) ? true : false;			
	}
}
