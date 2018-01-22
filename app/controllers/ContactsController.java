package controllers;

import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.ContactRepository;
import services.api.ContactService;

import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class ContactsController extends Controller {

    private ContactService contactService;

    @Inject
    public ContactsController(ContactService contactService){
        this.contactService = contactService;
    }


    public Result list(){
        ContactRepository repository = new ContactRepository();
        repository.save();
        return ok(Json.toJson(repository.findAll()));

    }

    public Result save(){
        return created(request().body().asJson());
    }
}
