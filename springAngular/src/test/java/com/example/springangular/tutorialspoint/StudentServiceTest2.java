package com.example.springangular.tutorialspoint;

import com.example.springangular.dto.StudentDto;
import com.example.springangular.model.Student;
import com.example.springangular.repository.RoleRepository;
import com.example.springangular.repository.StudentRepository;
import com.example.springangular.service.StudentService;
import com.example.springangular.service_impl.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest2 {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(studentRepository,roleRepository,passwordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    @Test
    void saveStudent() {
        //given
        StudentDto student = getStudent1();
        //when
        underTest.saveStudent(student);
        //then
        ArgumentCaptor<Student> studentDtoArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentDtoArgumentCaptor.capture());
        Student capturedStudent = studentDtoArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void saveRole() {
    }

    @Test
    void addRoleToUser() {
    }

    @Test
    void getStudent() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void getStudentById() {
        //when
        underTest.getStudentById(1l);
        //then
        verify(studentRepository).findById(1l);
    }

    @Test
    void getStudentByName() {
    }

    @Test
    void updateStudentDetails() {
    }

    private StudentDto getStudent1(){
        StudentDto student = new StudentDto(1l,"shariar","Mirpur 10", "24","123456");
        return  student;
    }
}