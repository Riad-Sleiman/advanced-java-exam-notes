package fr.epita.datamodels;


//This is the Passenger class containing all the fields that make up a passenger with getters, setters, and a constructor
public class Passenger {
	
	private String name;
	private String PClass;
	private int age;
	private String sex;
	private int survived;
	
	public Passenger(String name, String pClass, int age, String sex, int survived) {
		super();
		this.name = name;
		PClass = pClass;
		this.age = age;
		this.sex = sex;
		this.survived = survived;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPClass() {
		return PClass;
	}
	public void setPClass(String pClass) {
		PClass = pClass;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getSurvived() {
		return survived;
	}
	public void setSurvived(int survived) {
		this.survived = survived;
	}
	
}
