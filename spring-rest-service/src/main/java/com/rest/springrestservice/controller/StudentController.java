package com.rest.springrestservice.controller;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.net.URI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.springrestservice.service.StudentService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.springrestservice.model.Role;
import com.rest.springrestservice.model.Student;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Slf4j
public class StudentController {
	
    private final StudentService studentService;
    
    //get student
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
			var data = studentService.getAllStudents();
			List<Student> collect = data.stream().map(x -> {x.setAge(0);return x;}).collect(Collectors.toList());
			return ResponseEntity.ok().body(collect);
		}
    	return ResponseEntity.ok().body(studentService.getAllStudents());
    }
    
    //get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id){
    	return ResponseEntity.ok().body(studentService.getStudentById(id));
    }
    
    
  //get student by name
    @GetMapping("/students/profile/{name}")
    public ResponseEntity<Optional<Student>> getStudentByName(@PathVariable String name){
    	return ResponseEntity.ok().body(studentService.getStudentByName(name));
    }
    
    //save student
    @PostMapping("/students")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    	URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/students").toUriString());
    	return ResponseEntity.created(uri).body(studentService.saveStudent(student));
    }
    
    //update student
    @PutMapping("/students/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Student> updateStudentsDetails(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudentDetails(id,updatedStudent);
        return ResponseEntity.ok(student);
    }
    
    //delete student
    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }
    
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
    	URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/roles").toUriString());
    	return ResponseEntity.created(uri).body(studentService.saveRole(role));
    }
    
    @PostMapping("/addRoleToUser")
    public ResponseEntity<Role> addUserToRole(@RequestBody RoleToUserForm form) {
    	studentService.addRoleToUser(form.getUserName(), form.getRoleName());
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
    	String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    	
    	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Student student = studentService.getStudent(username);
				String access_token = JWT.create()
						.withSubject(student.getName())
						.withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", student.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashMap<String, String>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

				
			
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error logging in: {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Rfresh token is missing");
		}
    }
    
}

@Data
class RoleToUserForm{
	private String userName;
	private String roleName;
}
