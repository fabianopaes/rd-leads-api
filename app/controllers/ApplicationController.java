package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import resources.WelcomeResource;

public class ApplicationController extends Controller{


    public Result preflight(String all) {
        response().setHeader("Access-Control-Allow-Origin", "https://rd-leads-webapp.herokuapp.com");
        response().setHeader("Access-Control-Allow-Credentials'", "true");
        response().setHeader("Allow", "*");
        response().setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
        response().setHeader("Content-Type", "text/plain charset=UTF-8");
        return ok();
    }

    public Result home(){
        WelcomeResource resource = WelcomeResource.create();
        return ok(Json.toJson(resource));
    }


}