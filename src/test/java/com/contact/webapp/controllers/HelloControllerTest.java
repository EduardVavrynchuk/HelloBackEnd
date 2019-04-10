package com.contact.webapp.controllers;

import com.contact.db.entities.Contact;
import com.contact.db.repositories.ContactRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Autowired
    private ContactRepository contactRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addContact() {
        String name = "someContactName";

        ResponseEntity<?> contacts = helloController.addContact(name);
        assertEquals(HttpStatus.OK, contacts.getStatusCode());

        contacts = helloController.addContact(name);
        assertEquals(HttpStatus.CONFLICT, contacts.getStatusCode());

        contacts = helloController.addContact("");
        assertEquals(HttpStatus.BAD_REQUEST, contacts.getStatusCode());

        assertNotNull(contactRepository.findByName(name));
    }
}