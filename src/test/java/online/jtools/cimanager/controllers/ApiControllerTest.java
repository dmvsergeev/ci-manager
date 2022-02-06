package online.jtools.cimanager.controllers;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SecurityConfiguration.class
        ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {ApiControllerTest.Initializer.class})
@TestPropertySource(locations = "classpath:properties.properties")
public class ApiControllerTest extends TestCase {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @Before
    public void loginBeforeRequests() throws URISyntaxException {

        

    }

    @Test
    public void simpleCreateUser() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "0",
                "name", "Vacia",
                "email", "Vacia@mail.ru",
                "username", "Vacia"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        {
            // Body example: {"id":"6ef7b984-c118-4f03-b8a3-2f6624f65e45"}
            Assert.assertTrue(result.getBody().startsWith("{\"id\":\""));
            Assert.assertTrue(result.getBody().endsWith("\"}"));
            Assert.assertEquals(45, result.getBody().length());
        }
    }

    @Test
    public void setUserPasswordNotEmpty() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/password/set";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "1",
                "id_app", "8",
                "password", "123"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        {
            Assert.assertTrue(Boolean.parseBoolean(result.getBody()));
        }
    }

    @Test
    public void createUserWithoutEmail() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "0",
                "name", "Vacia",
                "email", "",
                "username", "Vacia"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(400, result.getStatusCodeValue());
        Assert.assertEquals("{\"code\":\"Empty email\",\"message\":\"Vacia has empty email\"}", result.getBody());
    }


    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}