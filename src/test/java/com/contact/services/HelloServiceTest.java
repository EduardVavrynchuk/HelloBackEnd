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

    private final static String FILTER_WITHOUT_A = "^A.*$";
    private final static String FILTER_WITHOUT_A_E_I = "^.*[aei].*$";

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

        generateNamesForFilterWhichDoesNotContainNamesWhatStartWith_A();
        assertEquals(amountNames + 2, helloService.getContactsByFilter(FILTER_WITHOUT_A).size());

        long amountNamesWithout_a_e_i = contactRepository.findAll().stream()
                .filter(contact -> !contact.getName().matches(FILTER_WITHOUT_A_E_I))
                .count();

        generateNamesForFilterWhichDoesNotContain_a_e_i();

        assertEquals(amountNamesWithout_a_e_i + 2, helloService.getContactsByFilter("^.*[aei].*$").size());
    }

    private void generateNamesForFilterWhichDoesNotContainNamesWhatStartWith_A() {
        String nameStartWith_A = "Arthur"; // not be shown
        String nameStartWith_a = "arthur";
        String nameNotStartWith_A = "Leonid";

        assertTrue(helloService.addContact(nameStartWith_A));
        assertTrue(helloService.addContact(nameStartWith_a));
        assertTrue(helloService.addContact(nameNotStartWith_A));
    }

    private void generateNamesForFilterWhichDoesNotContain_a_e_i() {
        String nameDoesNotContain_a_e_i = "Tun";
        String nameContain_e = "Luke";
        String nameDoesNotContain_a_e_i_2 = "John";

        assertTrue(helloService.addContact(nameDoesNotContain_a_e_i));
        assertTrue(helloService.addContact(nameContain_e));
        assertTrue(helloService.addContact(nameDoesNotContain_a_e_i_2));
    }
}