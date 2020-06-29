package com.epam.ct.training.core.router;

import com.epam.ct.training.core.entity.User;
import com.epam.ct.training.core.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRouterTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        webTestClient.get()
                .uri("/users")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetUser() {
        webTestClient.get()
                .uri("/users/1")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testAddUser() {
        webTestClient.post()
                .uri("/users")
                .body(Mono.just(new User("3")), User.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        Assertions.assertEquals(3, userService.getUsers().size());
    }
}
