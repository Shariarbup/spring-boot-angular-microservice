package com.example.springangular;

import com.example.springangular.model.Role;
import com.example.springangular.model.Student;
import com.example.springangular.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringAngularApplicationTests {
    private PasswordEncoder passwordEncoder;
    SpringAngularApplicationTests(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void contextLoads() {
        System.out.println(this.passwordEncoder.encode("123456"));
    }

//    @Autowired
//    private StudentRepository studentRepository;
//
//    public void isStudentExistById(){
//        Role role = new Role(1L,"ROLE_ADMIN");
//        List<Role> roles = new ArrayList<>();
//        roles.add(role);
//        Student student = new Student(12L, "Md AL Shriar", "Mirpur 10","25", "123456",roles);
//        studentRepository.save(student);
//        Boolean actualResult = studentRepository.existsById(12L);
//        assertThat(actualResult).isTrue();
//    }

}
