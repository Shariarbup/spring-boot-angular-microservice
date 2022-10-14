package com.rest.springrestservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rest.springrestservice.model.Role;
import com.rest.springrestservice.model.Student;

public interface StudentService {
	Student saveStudent(Student student);

	Role saveRole(Role role);

	void addRoleToUser(String userName, String roleName);

	Student getStudent(String studentName);

	List<Student> getAllStudents();

	Map<String, Boolean> deleteStudentById(Long id);

	Optional<Student> getStudentById(Long id);
	
	Optional<Student> getStudentByName(String name);

	Student updateStudentDetails(Long id, Student updatedStudent);

}
