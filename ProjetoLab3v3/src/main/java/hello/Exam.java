package hello;

import java.util.List;
import java.util.LinkedList;

public class Exam {

	private String title;
	private String type;
	private String description;
	private String author;
	private String imagem;
	private List<Question> questions = new LinkedList<Question>();
	
	// Constructor method
	public Exam(String title, String type, String description, String author, String imagem ){
		this.setTitle(title);
		this.setDescription(description);
		this.setAuthor(author);
		this.setImagem(imagem);
	}
	
	// Get-Set for title
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	// Get-Set for imagem
		public String getImagem(){
			return this.imagem;
		}
		public void setImagem(String imagem){
			this.imagem = imagem;
		}
	
	// Get-Set for type
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	// Get-Set for description
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	// Get-Set for author
	public String getAuthor(){
		return this.author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	
	// Get for questions
	public List<Question> getQuestions(){
		return this.questions;
	}
	public void setQuestions(List<Question> questions){
		this.questions = questions;
	}
	
	public Question getQuestion(String questionTitle){
		for(Question question : getQuestions()){
			if(question.isValid(questionTitle)){
				return question;
			}
		}
		
		return null;
	}
	
	// Add question for the exam
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	// Delete question from the list of questions of the exam
	public void delQuestion(String questionTitle){
		for(Question item: this.getQuestions()){
			if(item.isValid(questionTitle)){
				this.questions.remove(item);
			}
		}
	}
	
	// Check compatibility between two exams
	public boolean isValid(Exam exam){
		return this.getTitle().equals(exam.getTitle()) ? true : false;
	}
}
