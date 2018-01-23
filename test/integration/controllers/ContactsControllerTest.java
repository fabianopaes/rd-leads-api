package integration.controllers;

import config.FakeApplicationInit;
import models.Contact;
import models.Page;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import play.libs.Json;
import play.mvc.Result;
import resources.ContactResource;
import resources.PageResource;
import utils.ModelFactory;
import utils.ResourceFactory;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

public class ContactsControllerTest extends FakeApplicationInit {

    @Test
    public void whenTheresNoContactReturnsOkAndEmptyList(){
        Result result = callAction(controllers.routes.ref.ContactsController.list(),
                fakeRequest(GET, controllers.routes.ContactsController.list().url()));
        assertThat(status(result)).isEqualTo(OK);
        assertTrue("[]".equals( contentAsString(result)));
    }

    @Test
    public void whenTheresAtLeastOneContactReturnsAllData(){
        Contact contactExpected = ModelFactory.createAndSaveFullContact();
        Result result = callAction(controllers.routes.ref.ContactsController.list(),
                fakeRequest(GET, controllers.routes.ContactsController.list().url()));
        assertThat(status(result)).isEqualTo(OK);
        List<Contact> list = Arrays.asList(Json.fromJson(Json.parse(contentAsString(result)), Contact[].class));
        assertEquals(1, list.size());
        Contact contactGot = list.get(0);
        assertEquals(contactExpected.email, contactGot.email);
        assertEquals(contactExpected.id, contactGot.id);
        assertEquals(contactExpected.pages.size(), contactGot.pages.size());
    }

    @Test
    public void whenNotExistsTheGivenContactReturnsNotFound(){
        Result result = callAction(controllers.routes.ref.ContactsController.byId(5l),
                fakeRequest(GET, controllers.routes.ContactsController.byId(5l).url()));
        assertThat(status(result)).isEqualTo(NOT_FOUND);
    }

    @Test
    public void whenContactExistsReturnsItsAllData(){
        Contact contactExpected = ModelFactory.createAndSaveFullContact();
        Result result = callAction(controllers.routes.ref.ContactsController.byId(contactExpected.getId()),
                fakeRequest(GET, controllers.routes.ContactsController.byId(contactExpected.getId()).url()));
        assertThat(status(result)).isEqualTo(OK);
        Contact contactGot = Json.fromJson(Json.parse(contentAsString(result)), Contact.class);
        assertEquals(contactExpected.email, contactGot.email);
        assertEquals(contactExpected.id, contactGot.id);
        assertEquals(contactExpected.pages.size(), contactGot.pages.size());
    }

    @Test
    public void whenTryingToGetPagesButContactNotExistsReturnsNotFound(){
        Result result = callAction(controllers.routes.ref.ContactsController.getPages(5l),
                fakeRequest(GET, controllers.routes.ContactsController.getPages(5l).url()));
        assertThat(status(result)).isEqualTo(NOT_FOUND);
    }

    @Test
    public void whenTryingToGetPagesFromContactThatHasNoPagesReturnsEmptyList(){
        Contact contactExpected = ModelFactory.createAndSaveOnlyContact();
        Result result = callAction(controllers.routes.ref.ContactsController.getPages(contactExpected.getId()),
                fakeRequest(GET, controllers.routes.ContactsController.getPages(contactExpected.getId()).url()));
        assertThat(status(result)).isEqualTo(OK);
        List<Page> list = Arrays.asList(Json.fromJson(Json.parse(contentAsString(result)), Page[].class));
        assertThat(status(result)).isEqualTo(OK);
        assertTrue(list.isEmpty());
    }

    @Test
    public void wheGettingPagesFromContactReturnsItsAllPageList(){
        Contact contact = ModelFactory.createAndSaveFullContact();
        Result result = callAction(controllers.routes.ref.ContactsController.getPages(contact.getId()),
                fakeRequest(GET, controllers.routes.ContactsController.getPages(contact.getId()).url()));
        assertThat(status(result)).isEqualTo(OK);
        List<Page> list = Arrays.asList(Json.fromJson(Json.parse(contentAsString(result)), Page[].class));
        assertThat(status(result)).isEqualTo(OK);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 1);
        Page page = list.get(0);
        Page pageExpected = contact.pages.get(0);
        assertEquals(pageExpected.getId(), page.getId());
        assertEquals(pageExpected.url, page.url);
    }
    @Test
    public void whenTryingToCreateContactWithNoEmailReturnsBadRequest(){
        ContactResource resource = ResourceFactory.createContactResource("", "localhost");
        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                        .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void whenTryingToCreateContactWithInvalidEmailReturnsBadRequest(){
        ContactResource resource = ResourceFactory.createContactResource("email", "localhost");
        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                        .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void whenTryingToCreateContactReturnsCreated(){
        ContactResource resource = ResourceFactory.createContactResource("email@sunny-day.com", "http://anotherPage");
        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                        .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );

        assertThat(status(result)).isEqualTo(CREATED);

        assertEquals(1, getAppInitializer().getContactServiceInstance().findAll().size());
        Contact contactGot = Json.fromJson(Json.parse(contentAsString(result)), Contact.class);
        assertNotNull(contactGot.getId());

        assertFalse(contactGot.pages.isEmpty());
        assertTrue(contactGot.email.equals(resource.getEmail()));

        Contact fromDb = getAppInitializer().getContactRepositoryInstance().findById(contactGot.getId());
        assertFalse(CollectionUtils.isEmpty(fromDb.pages));
        assertTrue(fromDb.email.equals(resource.getEmail()));
    }

    @Test
    public void whenTryingToCreateContactButItExistsOnlyAddThePageToIt(){
        Contact contact = ModelFactory.createAndSaveFullContact();
        ContactResource resource = ResourceFactory.createContactResource(contact.email,
                Arrays.asList(new PageResource("http://anotherPage"), new PageResource("http://other-page-is-ready")));


        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                        .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );

        assertThat(status(result)).isEqualTo(CREATED);

        assertEquals(1, getAppInitializer().getContactServiceInstance().findAll().size());
        Contact contactGot = Json.fromJson(Json.parse(contentAsString(result)), Contact.class);
        assertNotNull(contactGot.getId());

        assertFalse(contactGot.pages.isEmpty());
        assertEquals(3, contactGot.pages.size());
        assertTrue(contactGot.email.equals(resource.getEmail()));

        Contact fromDb = getAppInitializer().getContactRepositoryInstance().findById(contactGot.getId());
        assertFalse(CollectionUtils.isEmpty(fromDb.pages));
        assertTrue(fromDb.email.equals(resource.getEmail()));

    }


    @Test
    public void whenTryingToSaveContactWithNoPagesReturnCreated(){
        ContactResource resource = new ContactResource("email@email.com");
        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                        .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );
        assertThat(status(result)).isEqualTo(CREATED);
        Contact contactGot = Json.fromJson(Json.parse(contentAsString(result)), Contact.class);
        assertNotNull(contactGot.getId());
        assertTrue(contactGot.pages.isEmpty());
        assertTrue(contactGot.email.equals(resource.getEmail()));

        Contact fromDb = getAppInitializer().getContactRepositoryInstance().findById(contactGot.getId());
        assertTrue(fromDb.pages.isEmpty());
        assertTrue(fromDb.email.equals(resource.getEmail()));
    }

    @Test
    public void whenTryingToSaveContactWithNoURLOnPagesReturnsBadRequest(){
        ContactResource resource = ResourceFactory.createContactResource("email@email.com", "");
        Result result = callAction(controllers.routes.ref.ContactsController.save(),
                fakeRequest(POST, controllers.routes.ContactsController.save().url())
                .withJsonBody(Json.toJson(resource)).withHeader("Content-Type", "application/json")
        );
        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }



}
