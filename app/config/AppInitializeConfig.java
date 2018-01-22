package config;

import repository.ContactRepository;
import services.api.ContactService;
import services.impl.ContactServiceImpl;

public class AppInitializeConfig extends AbstractConfig {

    @Override
    public ContactService getContactServiceInstance() {
        return new ContactServiceImpl(getContactRepositoryInstance());
    }

    @Override
    public ContactRepository getContactRepositoryInstance() {
        return new ContactRepository();
    }

}
