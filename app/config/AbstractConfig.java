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

}
