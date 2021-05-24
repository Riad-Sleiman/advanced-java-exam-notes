package fr.epita.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class Answer {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="TEXT")
	private String text;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_QUESTION"))
	private Question question;

	

	public Answer(int id, String text, Question question) {
		super();
		this.id = id;
		this.text = text;
		this.question = question;
	}

	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
