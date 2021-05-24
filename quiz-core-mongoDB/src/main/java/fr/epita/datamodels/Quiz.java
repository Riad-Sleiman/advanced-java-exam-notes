package fr.epita.datamodels;

import java.util.List;

public class Quiz {

	private String id;
	private String name;
	private int overallDifficulty;
	private List<Question> questions;
	
	
	
	
	public Quiz(String id) {
		super();
		this.id = id;
	}
	public Quiz(String id, String name, int overallDifficulty, List<Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.overallDifficulty = overallDifficulty;
		this.questions = questions;
	}
	public Quiz( String name, int overallDifficulty, List<Question> questions) {
		super();
		this.name = name;
		this.overallDifficulty = overallDifficulty;
		this.questions = questions;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOverallDifficulty() {
		return overallDifficulty;
	}
	public void setOverallDifficulty(int overallDifficulty) {
		this.overallDifficulty = overallDifficulty;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
}
