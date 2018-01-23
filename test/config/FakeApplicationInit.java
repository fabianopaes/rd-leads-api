package config;

import org.junit.After;
import org.junit.Before;
import play.test.FakeApplication;
import play.test.Helpers;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class FakeApplicationInit extends Helpers{

    private FakeApplication app;
    private AppInitializerTestConfig appInitializerConfig;

    public AppInitializerTestConfig getAppInitializer() {
        if (isNull(appInitializerConfig)) {
            appInitializerConfig = new AppInitializerTestConfig();
        }
        return appInitializerConfig;
    }

    @Before
    public void newFakeApp() {
        app = fakeApplication(configProvider(), getAppInitializer());
        start(app);
        inMemoryDatabase();
    }

    @After
    public void stopApp() {
        stop(app);
    }


    protected Map<String, Object> configProvider(){
        final Map<String, Object> configs = new HashMap<String, Object>();

        configs.put("ebean.default", "models.*");
        configs.put("db.default.user", "");
        configs.put("db.default.password", "");

        return configs;
    }
}
