package com.example.springbatch.Configuration;


import com.example.springbatch.Listener.JobCompletionNotificationListener;
import com.example.springbatch.Mapper.PersonMapper;
import com.example.springbatch.Processor.PersonProcessor;
import com.example.springbatch.model.Person;
import com.example.springbatch.model.ResponseODM;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Person Processor obj that has a method that returns the modified person data
    @Bean
    public PersonProcessor processor() {
        return new PersonProcessor();
    }


    @Bean
    public ItemReader<Person> reader() {
        return new JdbcCursorItemReaderBuilder<Person>()
                .dataSource(this.dataSource)
                .name("personReader")
                .sql("SELECT * FROM PERSON")
                .rowMapper(new PersonMapper())
                .build();
    }

    // writes data back to the database
    @Bean
    public JdbcBatchItemWriter<ResponseODM> writer() {
        return new JdbcBatchItemWriterBuilder<ResponseODM>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO responseOdm (FIRSTNAME, " +
                        "AMOUNT, YEARLYINCOME, STATUS, MESSAGES) " +
                        "VALUES (:firstName, :amount, :yearlyIncome, :status, :messages)")
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
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, ResponseODM>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
