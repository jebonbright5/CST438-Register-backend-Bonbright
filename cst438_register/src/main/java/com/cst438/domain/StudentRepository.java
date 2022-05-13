package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	// declare the following method to return a single Student object
	// default JPA behavior that findBy methods return List<Student> except for findById.
	public Student findByEmail(String email);

	//This causes orElse to break
//	public Student findById(int student_id);
	
//	@SuppressWarnings("unchecked")
//	Enrollment save(Enrollment e);
}
