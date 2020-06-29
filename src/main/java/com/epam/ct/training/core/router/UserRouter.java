package com.epam.ct.training.core.router;

import com.epam.ct.training.core.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> getUserRoute(UserHandler userHandler) {
        return route(RequestPredicates.GET("/users"), userHandler::getUsers)
                .and(route(RequestPredicates.GET("/users/{id}"), userHandler::getUser));
    }

    @Bean
    public RouterFunction<ServerResponse> addUserRoute(UserHandler userHandler) {
        return route(RequestPredicates.POST("/users")
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                userHandler::addUser);
    }
}
