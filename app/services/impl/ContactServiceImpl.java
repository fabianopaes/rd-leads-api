package services.impl;

import com.google.inject.Inject;
import models.Contact;
import repository.ContactRepository;
import services.api.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;

    @Inject
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void resgister() {

    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

}
