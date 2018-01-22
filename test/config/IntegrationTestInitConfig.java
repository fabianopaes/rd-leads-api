package config;

import repository.ContactRepository;
import services.api.ContactService;

public class IntegrationTestInitConfig extends AbstractConfig{

    @Override
    public ContactService getContactServiceInstance() {
        return null;
    }

    @Override
    public ContactRepository getContactRepositoryInstance() {
        return null;
    }

}
