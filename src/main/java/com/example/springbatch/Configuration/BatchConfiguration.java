package com.example.springbatch.Configuration;


import com.example.springbatch.Listener.JobCompletionNotificationListener;
import com.example.springbatch.Processor.PersonProcessor;
import com.example.springbatch.Reader.PersonItemReader;
import com.example.springbatch.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    // to build the jobs
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    // to build the steps
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    private static final String NAME = "initialName";
    private static final String AGE = "20";

    // Person Processor obj that has a method that returns the modified person data
    @Bean
    public PersonProcessor processor() {
        return new PersonProcessor();
    }

    @StepScope
    @Bean
    ItemReader<Person> reader(@Value("#{jobParameters[name]}") String names,
                              @Value("#{jobParameters[age]}") String age) {
        return new PersonItemReader(names, age);
    }

    // writes data back to the database
    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO person (name,age) VALUES (:name, :age)")
                .dataSource(this.dataSource)
                .build();
    }

    // create job with job listener execution
    @Bean
    public Job importPersonJob(
            JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importPersonJob")
                .incrementer(new RunIdIncrementer())
                .listener((JobExecutionListener) listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader(NAME, AGE))
                .processor(processor())
                .writer(writer)
                .build();
    }
}
