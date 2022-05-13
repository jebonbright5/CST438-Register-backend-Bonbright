package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/student")
	@Transactional
	public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
		System.out.println("addStudent Called.");

		Student studentData = studentRepository.findByEmail(studentDTO.email); // Create student entity

		if (studentData == null) {
			// Copy student name, email from student data to entity
			Student student = new Student();

			student.setName(studentDTO.name);
			student.setEmail(studentDTO.email);

			Student savedStudent = studentRepository.save(student);

			StudentDTO result = createStudentDTO(savedStudent);

			return result;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student already added");
		}
	}

	
	@PutMapping("/student/placeHold/{student_id}")
	@Transactional
	public void placeHold(@PathVariable int student_id) {
		System.out.println("/placeHold Called");

		Student student = studentRepository.findById(student_id).orElse(null);

		System.out.println(student.toString());

		if (student != null && student.getStatusCode() == 0) {
			student.setStatusCode(1);
		} else {
			// something is not right with the enrollment.
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student_id invalid. " + student_id);
		}

	}
	
	
	@PutMapping("/student/removeHold/{student_id}")
	@Transactional
	public void removeHold(@PathVariable int student_id) {
		System.out.println("/removeHold Called");

		Student student = studentRepository.findById(student_id).orElse(null);

		System.out.println(student.toString());

		if (student != null && student.getStatusCode() != 0) {
			student.setStatusCode(0);
		} else {
			// something is not right with the enrollment.
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student_id invalid or already on hold " + student_id);
		}

	}

	private StudentDTO createStudentDTO(Student s) {
		StudentDTO studentDTO = new StudentDTO();

		studentDTO.student_id = s.getStudent_id();
		studentDTO.name = s.getName();
		studentDTO.email = s.getEmail();
		studentDTO.statusCode = s.getStatusCode();
		studentDTO.status = s.getStatus();

		return studentDTO;
	}

}
