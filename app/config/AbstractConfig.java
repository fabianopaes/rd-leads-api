package config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.ContactsController;
import play.Application;
import play.GlobalSettings;
import repository.ContactRepository;
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
                requestStaticInjection(ContactsController.class);
            }
        });
    }

    @Override
    public <T> T getControllerInstance(Class<T> aClass) {
        return injector.getInstance(aClass);
    }

    public abstract ContactService  getContactServiceInstance();

    public abstract ContactRepository  getContactRepositoryInstance();

}
