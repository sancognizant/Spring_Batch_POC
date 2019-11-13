package com.example.springbatch.Router;


import com.example.springbatch.Handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PersonRouter {
    @Bean
    public RouterFunction<ServerResponse> routeSample(PersonHandler handler) {
        return
                route(GET("/person/{name}/{age}").and(accept(MediaType.APPLICATION_JSON)), handler::getProduct);
    }
}
