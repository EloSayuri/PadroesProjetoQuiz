package hello;

public class Answer {
	
	private String answer;
	private boolean correct;
	
	public Answer(String answer, boolean correct){
		this.setAnswer(answer);
		this.setCorrect(correct);
	}
	
	// Get-Set for answer
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	// Get-Set to check or set if the answer is correct or not
	public boolean isCorrect() {
		return this.correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	// Check compatibility between two answers
	public boolean isValid(Answer answer){
		return this.getAnswer().equals(answer.getAnswer()) ? true : false;
	}
}
