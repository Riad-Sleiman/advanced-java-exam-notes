package fr.epita.datamodels;

public class Student {
	
	private String id;
	private String name;
	private String studentId;
	
	
	
	public Student(String name, String studentId) {
		super();
		this.name = name;
		this.studentId = studentId;
	}
	
	

	public Student(String id) {
		super();
		this.id = id;
	}



	public Student(String id, String name, String studentId) {
		super();
		this.id = id;
		this.name = name;
		this.studentId = studentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
