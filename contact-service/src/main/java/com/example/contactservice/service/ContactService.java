package com.example.contactservice.service;

import com.example.contactservice.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContactOfUser(Long userId);
}
