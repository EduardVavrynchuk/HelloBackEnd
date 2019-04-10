package com.contact.services;

import com.contact.db.repositories.ContactRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

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

        assertTrue(helloService.addContact(name));
        assertFalse(helloService.addContact(name));

        assertNotNull(contactRepository.findByName(name));
    }

    @Test
    public void getContactsByFilter() {
        long amountNames = contactRepository.count();

        generateNamesForFilterWhichDoesNotShowNamesWhatStartWith_A();
        assertEquals(amountNames + 2, helloService.getContactsByFilter("^A.*$").size());
    }

    private void generateNamesForFilterWhichDoesNotShowNamesWhatStartWith_A() {
        String nameStartWith_A = "Arthur"; // not be shown
        String nameStartWith_a = "arthur";
        String nameNotStartWith_A = "Leonid";

        assertTrue(helloService.addContact(nameStartWith_A));
        assertTrue(helloService.addContact(nameStartWith_a));
        assertTrue(helloService.addContact(nameNotStartWith_A));
    }
}