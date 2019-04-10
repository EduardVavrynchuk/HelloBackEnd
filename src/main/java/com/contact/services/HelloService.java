package com.contact.services;

import com.contact.db.entities.Contact;
import com.contact.db.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelloService {

    private final ContactRepository contactRepository;

    @Autowired
    public HelloService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public boolean addContact(String name) {
        Contact contact = contactRepository.findByName(name);

        if (contact == null) {
            contactRepository.save(new Contact(name));
            return true;
        } else {
            return false;
        }

    }

    public List<Contact> getContactsByFilter(String nameFilter) {
        Page<Contact> contactPage = contactRepository.findAll(generatePageable());

        if (contactPage.hasContent()) {
            List<Contact> contactList = performFilter(contactPage, nameFilter);

            while (contactPage.hasNext()) {
                contactPage = contactRepository.findAll(contactPage.nextPageable());

                if (contactPage.hasContent()) {
                    contactList.addAll(performFilter(contactPage, nameFilter));
                }
            }

            return contactList;
        } else {
            return null;
        }
    }

    private Pageable generatePageable() {
        int size = 1000, page = 0;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        return PageRequest.of(page, size, sort);
    }

    private List<Contact> performFilter(Page<Contact> contactPage, String nameFilter) {
        return contactPage.getContent().parallelStream().filter(c -> !c.getName().matches(nameFilter)).collect(Collectors.toList());
    }
}
