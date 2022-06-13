package com.romeone.springapp;

import com.romeone.springapp.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringApplicationTests {

	@Autowired
	private AuthController auth;

	@Test
	void contextLoads() {
		assertThat(auth).isNotNull();
	}
}
