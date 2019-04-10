package com.contact.webapp.transferobject;

import com.contact.db.entities.Contact;

import java.util.List;

public class Contacts {

    public List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
