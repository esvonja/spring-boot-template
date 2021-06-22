package au.com.developer.api.web.controller.rest;

import au.com.developer.api.AbstractUnitTest;
import au.com.developer.api.common.error.CustomRestExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiControllerV1Test extends AbstractUnitTest {

    private ApiControllerV1 apiControllerV1;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        apiControllerV1 = new ApiControllerV1();
        mockMvc = MockMvcBuilders.standaloneSetup(apiControllerV1)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
    }

    @Test
    public void testWrongHttpMethod() throws Exception {
        mockMvc.perform(put("/api/v1/welcome")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testWrongURL() throws Exception {
        mockMvc.perform(post("/api/v1/something")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testWrongURLTwoLevels() throws Exception {
        mockMvc.perform(post("/api/v1/something/else")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}
