package integration.controllers;

import config.FakeApplicationInit;
import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class HealthchecksControllerTest extends FakeApplicationInit{

    @Test
    public void whenCheckingApiHealthGetOkayAsResponse(){
        Result result = callAction(controllers.routes.ref.HealthchecksController.ping(),
                fakeRequest(GET, controllers.routes.HealthchecksController.ping().url()));
        assertThat(status(result)).isEqualTo(OK);
        assertEquals("okay", contentAsString(result));
    }
}
