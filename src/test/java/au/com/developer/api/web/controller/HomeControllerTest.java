package au.com.developer.api.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {
    private MockMvc homeController;

    @BeforeEach
    public void before() {
        HomeController controller = new HomeController();
        homeController = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGet() throws Exception {
        homeController.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("testpage"));
    }
}
