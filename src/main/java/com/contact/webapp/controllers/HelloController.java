package com.contact.webapp.controllers;

import com.contact.services.HelloService;
import com.contact.webapp.transferobject.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "/contacts")
    public ResponseEntity<Contacts> getContacts(@RequestParam("nameFilter") String nameFilter) {
        Contacts contacts = new Contacts(helloService.getContactsByFilter(nameFilter));

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<?> addContact(@RequestParam("name") String name) {
        if (name.isEmpty())
            return new ResponseEntity<>("Param name is empty", HttpStatus.BAD_REQUEST);

        if (helloService.addContact(name)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This contact is already exists", HttpStatus.CONFLICT);
        }
    }
}
