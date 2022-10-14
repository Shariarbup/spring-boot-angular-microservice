package com.example.contactservice.controller;

import com.example.contactservice.entity.Contact;
import com.example.contactservice.entity.Student;
import com.example.contactservice.service.ContactService;
import com.example.contactservice.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ReportService reportService;

    @RequestMapping("/user/{userId}")
    public List<Contact> getContacts(@PathVariable("userId") Long userId){
        return this.contactService.getContactOfUser(userId);
    }

    @PostMapping("/report/{format}")
    public String getReport(@PathVariable String format,@RequestBody List<Student> students) throws JRException, FileNotFoundException {
       return this.reportService.exportReport(format,students);
    }
}
