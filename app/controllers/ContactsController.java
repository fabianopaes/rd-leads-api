package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class ContactsController {

    public static Result list(){
        return ok("okay");
    }
}
