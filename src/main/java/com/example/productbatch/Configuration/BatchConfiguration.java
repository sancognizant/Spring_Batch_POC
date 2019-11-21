package com.example.productbatch.Configuration;

import com.example.productbatch.Listener.JobCompletionNotificationListener;
import com.example.productbatch.Mapper.ProductMapper;
import com.example.productbatch.Model.Product;
import com.example.productbatch.Model.ResponseDiscount;
import com.example.productbatch.Processor.ProductProcessor;
import org.springframework.batch.core.Job;
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

    // Product Processor obj that has a method that returns the modified person data
    @Bean
    public ProductProcessor processor() {
        return new ProductProcessor();
    }

    // read data from database and save in pojo
    @Bean
    public ItemReader<Product> reader() {
        return new JdbcCursorItemReaderBuilder<Product>()
                .dataSource(this.dataSource)
                .name("productReader")
                .sql("SELECT * FROM PRODUCT")
                .rowMapper(new ProductMapper())
                .build();
    }

    // writes data back to the database
    @Bean
    public JdbcBatchItemWriter<ResponseDiscount> writer() {
        return new JdbcBatchItemWriterBuilder<ResponseDiscount>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO responseDiscount (DISCOUNT, " +
                        "MESSAGE) " +
                        "VALUES (:discount, :message)")
                .dataSource(this.dataSource)
                .build();
    }

    // create job with job listener execution
    @Bean
    public Job importProductJob(
            JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importProductJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Product, ResponseDiscount>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
