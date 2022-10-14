package com.example.springangular.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.springangular.dto.StudentDto;
import com.example.springangular.model.Role;
import com.example.springangular.model.Student;

public interface StudentService {
	Student saveStudent(StudentDto student);

	Role saveRole(Role role);

	void addRoleToUser(String userName, String roleName);

	Student getStudent(String studentName);

	List<Student> getAllStudents(Integer pageNumber, Integer pageSize);

	Map<String, Boolean> deleteStudentById(Long id);

	Optional<Student> getStudentById(Long id);
	
	Optional<Student> getStudentByName(String name);

	Student updateStudentDetails(Long id, Student updatedStudent);

}
