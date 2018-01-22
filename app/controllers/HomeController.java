package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import resources.WelcomeResource;

public class HomeController extends Controller {

    public Result home(){
        WelcomeResource resource = WelcomeResource.create();
        return ok(Json.toJson(resource));
    }
}
