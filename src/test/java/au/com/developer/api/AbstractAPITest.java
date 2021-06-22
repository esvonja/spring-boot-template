package au.com.developer.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContextManager;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "integration")
public abstract class AbstractAPITest {

    private TestContextManager testContextManager;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUpContext() throws Exception {
        // Do manually what the SpringJUnit4ClassRunner would normally do for us
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    protected String getApiURL() {
        return "http://localhost:" + port + "/api/v1/welcome";
    }
}
