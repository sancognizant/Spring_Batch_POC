package com.example.springbatch.Handler;


import com.example.springbatch.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class PersonHandler {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    // get 1 product from the database
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        String name = request.pathVariable("name");
        String age = request.pathVariable("age");

      // if the server response cannot be found
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        int _age = Integer.parseInt(age);
       // Person p = new Person(name, _age);


          // execute job through url call
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("name", name)
                    .addString("age",age)
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(null), Person.class).switchIfEmpty(notFound);
    }
}
