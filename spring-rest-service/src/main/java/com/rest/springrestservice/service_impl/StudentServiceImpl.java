package com.rest.springrestservice.service_impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springrestservice.exception.ResourceNotFoundException;
import com.rest.springrestservice.model.Role;
import com.rest.springrestservice.model.Student;
import com.rest.springrestservice.repository.RoleRepository;
import com.rest.springrestservice.repository.StudentRepository;
import com.rest.springrestservice.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional // jodi amra kono role set kori then eita automatic database e oitake save kore
				// dibe
@Slf4j
public class StudentServiceImpl implements StudentService, UserDetailsService {
	private final StudentRepository studentRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student student = studentRepository.findByName(username);
		if (student == null) {
			log.error("User not found in the database instance.");
			throw new UsernameNotFoundException("User not found in the database instance.");
		} else {
			log.info("User found in the database {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		student.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(student.getName(), student.getPassword(),
				authorities);
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		log.info("Saving new user {} to the database", student.getName());
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return studentRepository.save(student);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		log.info("Saving new role {} to the database", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		// TODO Auto-generated method stub
		log.info("Adding role {} to user {}", roleName, userName);
		Student student = studentRepository.findByName(userName);
		Role role = roleRepository.findByName(roleName);
		student.getRoles().add(role);

	}

	@Override
	public Student getStudent(String studentName) {
		// TODO Auto-generated method stub
		log.info("Fetching student{}", studentName);
		return studentRepository.findByName(studentName);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		log.info("Fetching all users");
		return studentRepository.findAll();
	}

	@Override
	public Map<String, Boolean> deleteStudentById(Long id) {
		studentRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}
	
	@Override
	public Optional<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(studentRepository.findByName(name));
	}

	@Override
	public Student updateStudentDetails(Long id, Student updatedStudent) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Info not found"));
		student.setName(updatedStudent.getName());
		student.setAddress(updatedStudent.getAddress());
		student.setPassword(updatedStudent.getPassword());
		studentRepository.save(student);
		return student;
	}
	
}
