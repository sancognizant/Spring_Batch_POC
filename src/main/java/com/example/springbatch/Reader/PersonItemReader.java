package com.example.springbatch.Reader;

import com.example.springbatch.model.Person;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


@StepScope
public class PersonItemReader implements ItemReader<Person> {

    private String name;
    private String age;

    public PersonItemReader(String name, String age) {
        this.name = name;
        this.age = age;
    }

    private boolean batchJobState = false;

    @Override
    public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!batchJobState) {
            int _age = Integer.parseInt(age);
            Person person = new Person();
            batchJobState = true;
            return person;
        }
        return null;
    }


}
