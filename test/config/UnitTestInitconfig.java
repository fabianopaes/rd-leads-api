package config;

import repository.ContactRepository;
import services.api.ContactService;

public class UnitTestInitconfig extends AbstractConfig {

    @Override
    public ContactService getContactServiceInstance() {
        return null;
    }

    @Override
    public ContactRepository getContactRepositoryInstance() {
        return null;
    }

}
