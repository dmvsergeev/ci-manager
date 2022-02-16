package online.jtools.cimanager.controllers;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityConfiguration.class
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {ApiControllerTest.Initializer.class})
@TestPropertySource(locations = "classpath:properties.properties")
public class ApiControllerTest extends TestCase {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    String setCookie;

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @Before
    public void loginBeforeRequests() throws URISyntaxException {
        String username = "admin";
        String password = "19056";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        final String baseUrl = "http://localhost:" + randomServerPort + "/login";

        ResponseEntity<String> entity = this.restTemplate.postForEntity(baseUrl,
                request, String.class);

        headers = entity.getHeaders();

        setCookie = Objects.requireNonNull(headers.get("Set-Cookie")).get(0);
    }

    @Test
    public void simpleCreateUser() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "0",
                "name", "Vacia",
                "email", "Vacia@mail.ru",
                "username", "Vacia"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        {
            final Map<String, String> obj = new Gson().fromJson(result.getBody(), Map.class);

            Assert.assertNotNull(obj.get("id"));
            Assert.assertEquals("Vacia", obj.get("name"));
            Assert.assertEquals("Vacia", obj.get("username"));
            Assert.assertNull(obj.get("password"));
        }
    }

    @Test
    public void setUserPasswordNotEmpty() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/password/set";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

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
    public void setUserPasswordEmpty() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/password/set";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "1",
                "id_app", "8",
                "password", ""), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request false
        Assert.assertEquals(400, result.getStatusCodeValue());

        final Map<String, String> obj = new Gson().fromJson(result.getBody(), Map.class);

        Assert.assertEquals("Empty field", obj.get("code"));
        Assert.assertEquals("has empty password", obj.get("message"));

    }

    @Test
    public void createUserWithoutEmail() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/user/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("id_user", "0",
                "name", "Vacia",
                "email", "",
                "username", "Vacia"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(400, result.getStatusCodeValue());

        final Map<String, String> obj = new Gson().fromJson(result.getBody(), Map.class);

        Assert.assertEquals("Empty field", obj.get("code"));
        Assert.assertEquals("Vacia has empty email", obj.get("message"));

    }

    @Test
    public void createValidApp() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/app/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("name", "Google",
                "url", "https://google.com"), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        {

            final Map<String, String> obj = new Gson().fromJson(result.getBody(), Map.class);

            Assert.assertEquals("Google", obj.get("name"));
            Assert.assertEquals("https://google.com", obj.get("url"));

        }
    }

    @Test
    public void createInvalidApp() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/app/create";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(Map.of("name", "",
                "url", ""), headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(400, result.getStatusCodeValue());

        final Map<String, String> obj = new Gson().fromJson(result.getBody(), Map.class);

        Assert.assertEquals("Empty field", obj.get("code"));
        Assert.assertEquals(" has empty name", obj.get("message"));

    }

    @Test
    public void checkAdminMenu() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/topmenu";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        {
            final List<Map<String, String>> obj = new Gson().fromJson(result.getBody(), List.class);
            Assert.assertEquals(7, obj.size());

            Assert.assertEquals("/passwords", obj.get(0).get("link"));
            Assert.assertEquals("Мои пароли", obj.get(0).get("name"));

            Assert.assertEquals("/news", obj.get(1).get("link"));
            Assert.assertEquals("Новости", obj.get(1).get("name"));

            Assert.assertEquals("/guides", obj.get(2).get("link"));
            Assert.assertEquals("Инструкции", obj.get(2).get("name"));

            Assert.assertEquals("/allusers", obj.get(3).get("link"));
            Assert.assertEquals("Пользователи", obj.get(3).get("name"));

            Assert.assertEquals("/createuser", obj.get(4).get("link"));
            Assert.assertEquals("Создать пользователя", obj.get(4).get("name"));

            Assert.assertEquals("/apps", obj.get(5).get("link"));
            Assert.assertEquals("Приложения", obj.get(5).get("name"));

            Assert.assertEquals("/createapp", obj.get(6).get("link"));
            Assert.assertEquals("Создать Приложение", obj.get(6).get("name"));
        }
    }

    @Test
    public void checkAdminRoleUrlAccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Cookie", setCookie);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        HashSet<String> listUrls = new HashSet<>();

        listUrls.add("/allusers");
        listUrls.add("/passwords");
        listUrls.add("/news");
        listUrls.add("/guides");
        listUrls.add("/apps");

        for (String url : listUrls) {

            URI uri = new URI(baseUrl + url);

            ResponseEntity<String> result = restTemplate.exchange(
                    uri, HttpMethod.GET, requestEntity, String.class);

            //Verify request succeed
            Assert.assertEquals(200, result.getStatusCodeValue());
        }
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