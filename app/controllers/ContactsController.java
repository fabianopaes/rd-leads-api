package controllers;

import play.libs.Json;
import play.mvc.Result;
import repository.ContactRepository;

import static play.mvc.Results.ok;

public class ContactsController {

    public static Result list(){
        ContactRepository repository = new ContactRepository();
        repository.save();
        return ok(Json.toJson(repository.findAll()));

    }
}
