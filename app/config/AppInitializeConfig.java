package config;

import repository.ContactRepository;
import repository.PageRepository;
import services.api.ContactService;
import services.impl.ContactServiceImpl;

public class AppInitializeConfig extends AbstractConfig {

    @Override
    public ContactService getContactServiceInstance() {
        return new ContactServiceImpl(getContactRepositoryInstance(), getPageRepositoryInstance());
    }

    @Override
    public ContactRepository getContactRepositoryInstance() {
        return new ContactRepository();
    }

    @Override
    public PageRepository getPageRepositoryInstance() {
        return new PageRepository();
    }
}
