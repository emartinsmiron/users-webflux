package br.com.eduardo.users;

import br.com.eduardo.users.model.User;
import br.com.eduardo.users.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiUserTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository repository;



    private User getUser(){
        return User.builder().id("1").name("Eduardo").username("emartinsmiron@gmail.com").build();
    }

    private User getUserFabiana(){
        return User.builder().id("1").name("Fabiana").username("fabianadoval@gmail.com").build();
    }


    @Test
    public void whenSaveShouldBeReturnCreated() {

        webTestClient
            .post()
            .uri(uriBuilder -> uriBuilder.path("/users").build())
            .body(BodyInserters.fromObject(getUser()))
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(User.class)
            .returnResult()
            .equals(getUser());
    }

    @Test
    public void whenSearchByIdShouldBeReturnOk() {

        webTestClient
            .get()
            .uri(uriBuilder -> uriBuilder.path("/users").path(String.format("/%s", getUser().getId())).build())
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectBody(User.class)
            .returnResult().equals(getUser());
    }

    @Test
    public void whenUpdateShouldBeReturnOk() {

        webTestClient
            .put()
            .uri(uriBuilder -> uriBuilder.path("/users").build())
            .body(BodyInserters.fromObject(getUserFabiana()))
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(User.class)
            .returnResult().equals(getUserFabiana());
    }

    @Test
    public void whenDeleteShouldBeReturnOk() {

        webTestClient
            .delete()
            .uri(uriBuilder -> uriBuilder.path("/users/1").build())
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus()
            .isOk();
    }





}
