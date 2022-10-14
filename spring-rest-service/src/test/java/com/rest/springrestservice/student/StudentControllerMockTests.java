package com.rest.springrestservice.student;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.Pattern.Flag;

import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.springrestservice.model.Role;
import com.rest.springrestservice.model.Student;
import com.rest.springrestservice.repository.StudentRepository;
import com.rest.springrestservice.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerMockTests {

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@Test
	public void saveStudentTest() throws Exception {
		Student student = getStudent();
		when(studentRepository.save(student)).thenReturn(student);

		assertEquals(student, studentService.saveStudent(student));
	}

	@Test
	public void testRetrieveStudentById() throws Exception {
		Role role1 = new Role(1l, "admin");
		ArrayList<Role> role = new ArrayList<Role>();
		role.add(role1);

		Optional<Student> optStudent = Optional.of(new Student(1l, "shariar", "mirpur 10", 24, "test", role));
		when(studentRepository.findById(1l)).thenReturn(optStudent);

		assertTrue(studentService.getStudentById(1l).get().getName().contains("shariar"));
	}

	public void testRetrievwStudentByName() throws Exception {
		Role role1 = new Role(1l, "admin");
		ArrayList<Role> role = new ArrayList<Role>();
		role.add(role1);
		String name = "shariar";
		when(studentRepository.findByName(name)).thenReturn(getStudent());
		assertTrue(studentService.getStudentByName(name).get().getName().contains("shariar"));
	}

	@Test
	public void getStudentListSizeTest() throws Exception {
		Role role1 = new Role(1l, "admin");
		ArrayList<Role> role = new ArrayList<Role>();
		role.add(role1);
		when(studentRepository.findAll())
				.thenReturn(Stream
						.of(new Student(1l, "shuvo", "jhenaidah", 24, "test", role),
								new Student(2l, "shariar", "mirpur 10", 24, "test", role))
						.collect(Collectors.toList()));
		assertEquals(2, studentService.getAllStudents().size());
	}

	@Test
	public void deleteStudentTest() throws Exception {
		Student student = getStudent();
		Map<String, Boolean> deleteStudentById = studentService.deleteStudentById(student.getId());
		Boolean flag = false;
		for (Map.Entry<String, Boolean> entry : deleteStudentById.entrySet()) {
			flag = entry.getValue();
			// do something with key and/or tab
		}
		assertEquals(true, flag);
	}

	private Student getStudent() {
		Role role1 = new Role(1l, "admin");
		ArrayList<Role> role = new ArrayList<Role>();
		role.add(role1);
		Student student = new Student();
		student.setId(1l);
		student.setName("shariar");
		student.setAge(30);
		student.setAddress("Dhaka Canttt");
		student.setPassword("test");
		student.setRoles(role);
		return student;
	}
}
