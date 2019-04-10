package com.contact.db.repositories;

import com.contact.db.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findAll();

    Page<Contact> findAll(Pageable pageable);

    Contact findByName(String name);
}
