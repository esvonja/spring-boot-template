package au.com.developer.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class, properties = {
        "ap.env=dev",
        "changeme.api-template.url=http://localhost:8080/api/v1/service"
})
@AutoConfigureMockMvc
public class ApplicationSmokeTest {

    @Autowired
    private MockMvc mvc;

    @Test
    // Basic smoke test to make sure the application can start (no missing properties, etc.)
    public void smokeTest() throws Exception {
        this.mvc.perform(get("/i-do-not-exist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
