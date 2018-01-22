package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class HealthchecksController {

    public Result ping(){
        return ok("ok;");
    }
}
