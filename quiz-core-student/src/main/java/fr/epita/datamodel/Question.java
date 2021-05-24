package fr.epita.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "QUESTIONS")
public class Question {
	@Column(name = "TITLE")
	private String subject;

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="DIFFICULTY")
	private int difficulty;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_QUIZ"))
	private Quiz quiz;
	
	

	public Question(String subject, int id, int difficulty, Quiz quiz) {
		super();
		this.subject = subject;
		this.id = id;
		this.difficulty = difficulty;
		this.quiz = quiz;
	}

	
	
	public Quiz getQuiz() {
		return quiz;
	}



	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Question [subject=" + subject + ", id=" + id + ", difficulty=" + difficulty + "]";
	}
}
