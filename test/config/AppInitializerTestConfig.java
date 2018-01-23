package config;

import repository.ContactRepository;
import repository.PageRepository;
import services.api.ContactService;
import services.impl.ContactServiceImpl;

import static org.mockito.Mockito.spy;

public class AppInitializerTestConfig extends AbstractConfig{

    @Override
    public ContactService getContactServiceInstance() {
        return spy(new ContactServiceImpl(
                getContactRepositoryInstance(), getPageRepositoryInstance()
        ));
    }

    @Override
    public ContactRepository getContactRepositoryInstance() {

        return spy(new ContactRepository());
    }

    @Override
    public PageRepository getPageRepositoryInstance() {
        return spy(new PageRepository());
    }
}
