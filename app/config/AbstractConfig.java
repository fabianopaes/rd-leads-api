package config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.ContactsController;
import play.Application;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.headers.SecurityHeadersFilter;
import repository.ContactRepository;
import repository.PageRepository;
import services.api.ContactService;

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
