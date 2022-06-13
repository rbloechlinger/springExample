package com.romeone.springapp.controller;

import com.romeone.springapp.ServletInitializer;
import com.romeone.springapp.payload.request.SignupRequest;
import com.romeone.springapp.repository.UserRepository;
import com.romeone.springapp.security.UrlAuthenticationSuccessHandler;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthControllerTest.class);


    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Before("")
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    void registerUser() throws Exception {
//        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Greetings from Spring Boot!")));


        MvcResult registerResult = this.mockMvc.perform(
                post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{" +
                                "\"username\": \"foo\"," +
                                "\"email\": \"email@example.com\"," +
                                "\"password\": \"123456789\"," +
                                "\"role\":[\"user\"]}"))
                .andExpect(status().isOk())
                .andReturn();

    }


    void deleteUser() throws Exception {
//        MvcResult registerResult = this.mockMvc.perform(
//                        post("/api/auth/signup")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .content("{ \"username\": \"foo\", \"email\": \"email@example.com\", \"password\": \"123456789\", \"role\":[\"user\"]}"))
//                .andExpect(status().isOk())
//                .andReturn();
    }

    @Test
    void authenticateUser() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
//        ResponseEntity<String> response = testRestTemplate.getForEntity(
//                FOO_RESOURCE_URL + "/1", String.class);

//        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


}