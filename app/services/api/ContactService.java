package services.api;

import models.Contact;
import resources.ContactResource;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    Contact save(ContactResource contact);

    List<Contact> findAll();

    Optional<Contact> findByIdOptional(Long id);

    Contact findById(Long id);


}
