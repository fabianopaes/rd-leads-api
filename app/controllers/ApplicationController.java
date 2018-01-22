package controllers;

import play.mvc.Controller;
import play.mvc.Result;

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


}