package com.example.Rest_API.service;


import com.example.Rest_API.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    void testEmail(){
        emailService.sendEmail("munwanihitesh@gmail.com","Xgen internship","Beta hogyi h apkiii Muabarkoo");
    }
}
