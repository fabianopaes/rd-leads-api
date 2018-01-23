package controllers;

import com.google.inject.Inject;
import models.Contact;
import play.data.Form;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import resources.ContactResource;
import services.api.ContactService;

import java.util.Optional;

public class ContactsController extends Controller{

    private ContactService contactService;

    @Inject
    public ContactsController(ContactService contactService){
        this.contactService = contactService;
    }

    public Result list(){
        return ok(Json.toJson(contactService.findAll()));
    }

    public Result byId(Long contactId){
        Optional<Contact> contact = contactService.findByIdOptional(contactId);
        if(contact.isPresent()){
            return ok(Json.toJson(contact.get()));
        }
        return notFound();
    }


    @BodyParser.Of(BodyParser.Json.class)
    @Transactional
    public Result save(){
        Form<ContactResource> requestForm = Form.form(ContactResource.class).bindFromRequest();

        if(requestForm.hasErrors()){
            return badRequest(requestForm.errorsAsJson());
        }

        Contact contact = contactService.save(requestForm.get());

        return created(Json.toJson(contact));
    }

    public Result getPages(Long contactId){
        Optional<Contact> contact = contactService.findByIdOptional(contactId);
        if(! contact.isPresent()){
            return notFound();
        }
        return ok(Json.toJson(contact.get().pages));
    }

}
