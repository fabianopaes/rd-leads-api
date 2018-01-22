package config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.ContactsController;
import play.Application;
import play.GlobalSettings;
import play.api.mvc.Results;
import play.libs.F;
import play.libs.Scala;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import repository.ContactRepository;
import repository.PageRepository;
import scala.Tuple2;
import scala.collection.Seq;
import services.api.ContactService;
import play.api.mvc.EssentialFilter;
import play.filters.headers.SecurityHeadersFilter;

import java.util.ArrayList;
import java.util.List;

import static play.core.j.JavaResults.BadRequest;
import static play.core.j.JavaResults.InternalServerError;
import static play.core.j.JavaResults.NotFound;

public abstract class AbstractConfig extends GlobalSettings {

    private Injector injector;

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
                bind(ContactService.class).toInstance(getContactServiceInstance());
                bind(ContactRepository.class).toInstance(getContactRepositoryInstance());
                bind(PageRepository.class).toInstance(getPageRepositoryInstance());
                requestStaticInjection(ContactsController.class);
            }
        });
    }

    public <T extends EssentialFilter> Class<T>[] filters() {
       return new Class[]{SecurityHeadersFilter.class};
    }

    @Override
    public <T> T getControllerInstance(Class<T> aClass) {
        return injector.getInstance(aClass);
    }

    public abstract ContactService  getContactServiceInstance();

    public abstract ContactRepository  getContactRepositoryInstance();

    public abstract PageRepository getPageRepositoryInstance();

/*

    private class ActionWrapper extends Action.Simple {
        public ActionWrapper(Action<?> action) {
            this.delegate = action;
        }

        @Override
        public F.Promise<Result> call(Http.Context ctx) throws java.lang.Throwable {
            F.Promise<Result> result = this.delegate.call(ctx);
            Http.Response response = ctx.response();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return result;
        }
    }

    */
/*
     * Adds the required CORS header "Access-Control-Allow-Origin" to successfull requests
     *//*

    @Override
    public Action<?> onRequest(Http.Request request, java.lang.reflect.Method actionMethod) {
        return new AbstractConfig.ActionWrapper(super.onRequest(request, actionMethod));
    }

    private static class CORSResult implements Result {
        final private play.api.mvc.Result wrappedResult;

        public CORSResult(Results.Status status) {
            List<Tuple2<String, String>> list = new ArrayList<Tuple2<String, String>>();
            Tuple2<String, String> t = new Tuple2<String, String>("Access-Control-Allow-Origin","*");
            list.add(t);
            Seq<Tuple2<String, String>> seq = Scala.toSeq(list);
            wrappedResult = status.withHeaders(seq);
        }

        public play.api.mvc.Result toScala() {
            return this.wrappedResult;
        }
    }

    */
/*
     * Adds the required CORS header "Access-Control-Allow-Origin" to bad requests
     *//*

    @Override
    public F.Promise<Result> onBadRequest(Http.RequestHeader request, String error) {
        return F.Promise.<Result>pure(new AbstractConfig.CORSResult(BadRequest()));
    }

    */
/*
     * Adds the required CORS header "Access-Control-Allow-Origin" to requests that causes an exception
     *//*

    @Override
    public F.Promise<Result> onError(Http.RequestHeader request, Throwable t) {
        return F.Promise.<Result>pure(new AbstractConfig.CORSResult(InternalServerError()));
    }

    */
/*
     * Adds the required CORS header "Access-Control-Allow-Origin" when a route was not found
     *//*

    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader request) {
        return F.Promise.<Result>pure(new AbstractConfig.CORSResult(NotFound()));
    }
*/

}
