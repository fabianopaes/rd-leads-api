package integration.controllers;

import config.FakeApplicationInit;
import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;

public class ApplicationControllerTest extends FakeApplicationInit {

    @Test
    public void whenAccessApiHomeGetWelcomeAsResponse(){
        Result result = callAction(controllers.routes.ref.ApplicationController.home(),
                fakeRequest(GET, controllers.routes.ApplicationController.home().url()));
        assertThat(status(result)).isEqualTo(OK);
    }

}
