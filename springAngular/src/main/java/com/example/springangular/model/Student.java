package com.example.springangular.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be blank.")
	@Column(nullable = false)
    private String name;

    @NotEmpty(message = "Address should not be blank.")
	@Column(nullable = false)
    private String address;

	@Column(nullable = false)
    @NotEmpty(message = "Age should not be blank.")
    private String age;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<Role>();

    public Student(Long id, String name, String address, String age, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.password = password;
    }
    public Student(String name, String address, String age, String password) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.password = password;
    }
}
