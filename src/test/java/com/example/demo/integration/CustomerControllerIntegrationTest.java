package com.example.demo.integration;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerIntegrationTest extends IntegrationTest {
    @Test
    public void shouldReturnTheListOfBusinessDepartments() throws Exception {

        this.mvc.perform(this.get("/customer?filter=1"))
                .andExpect(status().isOk());
    }
}
