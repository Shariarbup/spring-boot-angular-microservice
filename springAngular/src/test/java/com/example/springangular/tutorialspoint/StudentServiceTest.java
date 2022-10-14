package com.example.springangular.tutorialspoint;

import com.example.springangular.repository.RoleRepository;
import com.example.springangular.repository.StudentRepository;
import com.example.springangular.service.StudentService;
import com.example.springangular.service_impl.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private  RoleRepository roleRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    private StudentService underTest;

    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentServiceImpl(studentRepository,roleRepository,passwordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveStudent() {
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

}