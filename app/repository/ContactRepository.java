package repository;

import models.Contact;

import java.util.Optional;

public class ContactRepository extends BaseRepository<Contact> {

    public ContactRepository() {
        super(Contact.class);
    }

    public Contact findById(Long id) {
        return finder.byId(id);
    }

    public Contact findByEmail(String email) {
        return finder.where().eq("email", email).findUnique();
    }

    public Optional<Contact> findByEmailOptional(String email) {
        return Optional.ofNullable(findByEmail(email));
    }

}
