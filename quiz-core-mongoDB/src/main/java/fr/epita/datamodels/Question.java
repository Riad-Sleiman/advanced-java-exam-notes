package fr.epita.datamodels;

import java.util.List;

public class Question {

	private String id;
	private String subject;
	private List<Choice> choices;
	
	public Question(String id, String subject, List<Choice> list) {
		super();
		this.id = id;
		this.subject = subject;
		this.choices = list;
	}
	
	
	
	public Question(String id) {
		super();
		this.id = id;
	}



	public Question(String subject, List<Choice> choices) {
		super();
		this.subject = subject;
		this.choices = choices;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	
}
