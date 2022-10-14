package com.example.contactservice.service;

import com.example.contactservice.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService{

    //fake List of Contacts
    List<Contact> contacts = List.of(
            new Contact(1L,"shariar@gmail.com","shariar",11L),
            new Contact(2L,"sohel@gmail.com","sohel",12L),
            new Contact(3L,"jakaria@gmail.com","Jakaria",13L),
            new Contact(4L,"Mostafiz@gmail.com","Mostafiz",14L)
    );
    @Override
    public List<Contact> getContactOfUser(Long userId) {
        return this.contacts.stream().filter(contact -> contact.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
