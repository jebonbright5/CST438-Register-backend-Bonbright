package com.cst438.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.EnrollmentDTO;


public class GradebookServiceREST extends GradebookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	String gradebook_url;
	
	public GradebookServiceREST() {
		System.out.println("REST grade book service");
	}

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		
		//TODO  complete this method in homework 4
		
		//When a student add a class, 
		//send message to Gradebook 
		//backend using EnrollmentDTO.
		
		EnrollmentDTO msg = new EnrollmentDTO(student_email, student_name, course_id);
		
		System.out.println("	Sending message: " + msg);
		
		ResponseEntity<EnrollmentDTO> response = restTemplate.postForEntity(
				gradebook_url + "/enrollment/",		//URL "http://localhost:8081/schedule"
				msg,								//Data
				EnrollmentDTO.class);				//Return data type
		
		HttpStatus rc = response.getStatusCode();
		System.out.println("HttpStatus: " + rc);
	
	}

}
