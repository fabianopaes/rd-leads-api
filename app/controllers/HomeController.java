package controllers;

import play.mvc.Result;

public class HomeController {

    public static Result home(){
        return HealthchecksController.ping();
    }
}
