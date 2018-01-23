package utils;

import models.Contact;
import models.Page;

public class ModelFactory {

    public static Contact createAndSaveFullContact(){
        Contact contact = new Contact();
        contact.email = "email@email";
        contact.save();

        Page page = new Page();
        page.contact = contact;
        page.url = "http://localhost:8000";

        page.save();
        contact.refresh();
        return contact;
    }

    public static Contact createAndSaveOnlyContact(){
        Contact contact = new Contact();
        contact.email = "email@email";
        contact.save();
        return contact;
    }
}
