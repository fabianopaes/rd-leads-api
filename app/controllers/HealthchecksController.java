package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HealthchecksController extends Controller {

    public Result ping(){
        return ok("ok");
    }
}
