package com.epam.ct.training.core.handler;

import com.epam.ct.training.core.entity.User;
import com.epam.ct.training.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> getUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(Flux.fromIterable(userService.getUsers()), User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(userService.getUser(id)));
    }

    public Mono<ServerResponse> addUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .doOnNext(userService::addUser)
                .then(Mono.empty());
    }
}
