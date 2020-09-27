package com.Datamatics.ranjithmg.backend.repository;

import com.Datamatics.ranjithmg.backend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
