package com.romeone.springapp;


import io.github.robwin.swagger.test.SwaggerAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiSchemaIntegrationTest {
    @LocalServerPort
    int port;

    @Test
    public void validateThatImplementationMatchesDocumentationSpecification() {
        String apiContract = RestApiSchemaIntegrationTest.class.getResource("/swagger-contract.yaml").getPath();
        System.out.println(apiContract);

        String swaggerSchemaUrl = String.format("http://localhost:%d/v2/api-docs", port);
        SwaggerAssertions.assertThat(swaggerSchemaUrl).isEqualTo(apiContract);
    }
}
