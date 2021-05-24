package fr.epita.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHOICES")
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "TEXT")
	private String text;
	
	@Column(name="VALID")
	private boolean valid;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_QUESTION"))
	private Question question;
	
	

	public Choice(Integer id, String text, boolean valid, Question question) {
		super();
		this.id = id;
		this.text = text;
		this.valid = valid;
		this.question = question;
	}

	
	
	public Question getQuestion() {
		return question;
	}



	public void setQuestion(Question question) {
		this.question = question;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
