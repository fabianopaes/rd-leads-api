package services.impl;

import com.google.inject.Inject;
import models.Contact;
import models.Page;
import org.springframework.util.CollectionUtils;
import repository.ContactRepository;
import repository.PageRepository;
import resources.ContactResource;
import resources.PageResource;
import services.api.ContactService;

import java.util.List;
import java.util.Optional;

public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;

    private PageRepository pageRepository;

    @Inject
    public ContactServiceImpl(ContactRepository contactRepository, PageRepository pageRepository) {
        this.contactRepository = contactRepository;
        this.pageRepository = pageRepository;
    }

    @Override
    public Contact save(ContactResource resource) {

        Contact contact = contactRepository.findByEmailOptional(resource.getEmail())
                .orElseGet(()->{
                    Contact cont = new Contact();
                    cont.email = resource.getEmail();
                    contactRepository.save(cont);
                    return cont;
                });

        addPages(contact, resource.getPages());
        contact.refresh();

        return contact;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
   }

    @Override
    public Optional<Contact> findByIdOptional(Long id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id);
    }

    public void addPages(Contact contact, List<PageResource> pageResources){
        if(CollectionUtils.isEmpty(pageResources)){
            return;
        }
        pageResources.stream().forEach(page->addPage(contact, page));
    }

    public void addPage(Contact contact, PageResource pageResource){
        Page page = new Page();
        page.url = pageResource.getUrl();
        page.contact = contact;
        pageRepository.save(page);
    }


}
