package fr.epita.datamodels;

public class Answer {

	private String id;
	private String text;
	private Student student;
	private Question question;
	
	
	
	
	public Answer(String id) {
		super();
		this.id = id;
	}


	public Answer(String text, Student student, Question question) {
		super();
		this.text = text;
		this.student = student;
		this.question = question;
	}


	public Answer(String id, String text, Student student, Question question) {
		super();
		this.id = id;
		this.text = text;
		this.student = student;
		this.question = question;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
