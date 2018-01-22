package repository;

import models.Contact;

public class ContactRepository extends BaseRepository<Contact>{

    public ContactRepository() {
        super(Contact.class);
    }

    public Contact save(){
        Contact contact = new Contact();
        contact.name = "FabianoLindo";
        getEbeanServer().save(contact);
        return contact;
    }
}
