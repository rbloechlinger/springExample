package com.romeone.springapp.controller;

import com.romeone.springapp.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;


    @Test
    void registerUser() throws Exception {
//        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Greetings from Spring Boot!")));

        JSONObject jsonString = new JSONObject()
                .put("username","foo")
                .put("password", "1234")
                .put("email","email@example.com")
                .put("role", Arrays.asList("user"));


        System.out.println(jsonString.toString());

        MvcResult registerResult = this.mockMvc.perform(
                post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonString.toString()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void authenticateUser() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
//        ResponseEntity<String> response = testRestTemplate.getForEntity(
//                FOO_RESOURCE_URL + "/1", String.class);

//        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


}