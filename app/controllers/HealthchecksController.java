package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class HealthchecksController {

    public static Result ping(){
        return ok("ok;");
    }
}
