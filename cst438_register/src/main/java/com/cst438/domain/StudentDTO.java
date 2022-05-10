package com.cst438.domain;

public class StudentDTO {
	public int student_id; 
	public String name;
	public String email;
	public int statusCode;
	public String status;
	
	//Default Constructor
	public StudentDTO() {
		this.student_id = 0;
		this.name = null;
		this.email=null;
		this.statusCode=0;
		this.status = null;
	}
	
	public StudentDTO(int student_id, String name, String email, int statusCode, String status) {
		this.student_id = student_id;
		this.name = name;
		this.email = email;
		this.statusCode = statusCode;
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "StudentDTO [student_id=" + student_id + ", studentName=" + name +
				", studentEmail=" + email + ", statusCode= " 
				+ statusCode + ", status=" + status + "]";
	}
	
}
