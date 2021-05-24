package fr.epita.datamodels;

import java.util.List;

public class Exam {

	private String id;
	private String title;
	private Quiz quiz;
	private List<Answer> answers;
	
	
	
	public Exam(String id, String title, Quiz quiz, List<Answer> answers) {
		super();
		this.id = id;
		this.title = title;
		this.quiz = quiz;
		this.answers = answers;
	}
	public Exam(String title, Quiz quiz, List<Answer> answers) {
		super();
		this.title = title;
		this.quiz = quiz;
		this.answers = answers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
