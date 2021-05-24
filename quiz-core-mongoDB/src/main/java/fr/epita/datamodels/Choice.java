package fr.epita.datamodels;

public class Choice {

	private String id;
	private int optionNumber;
	private String choiceLabel;
	private boolean valid;
	
	public Choice(String id, int optionNumber, String choiceLabel, boolean valid) {
		super();
		this.id = id;
		this.optionNumber = optionNumber;
		this.choiceLabel = choiceLabel;
		this.valid = valid;
	}
	
	
	
	
	public Choice(int optionNumber, String choiceLabel, boolean valid) {
		super();
		this.optionNumber = optionNumber;
		this.choiceLabel = choiceLabel;
		this.valid = valid;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getOptionNumber() {
		return optionNumber;
	}
	public void setOptionNumber(int optionNumber) {
		this.optionNumber = optionNumber;
	}
	public String getChoiceLabel() {
		return choiceLabel;
	}
	public void setChoiceLabel(String choiceLabel) {
		this.choiceLabel = choiceLabel;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}
