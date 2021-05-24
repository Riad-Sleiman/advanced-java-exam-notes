package fr.epita.datamodel;

public class Passenger {
	private String name;
	private String pClass;
	private int age;
	private String sex;
	private int survived;
	
	
	
	public Passenger(String name, String pClass, int age, String sex, int survived) {
		super();
		this.name = name;
		this.pClass = pClass;
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
	public String getpClass() {
		return pClass;
	}
	public void setpClass(String pClass) {
		this.pClass = pClass;
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
