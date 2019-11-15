package com.example.springbatch.Processor;

import com.example.springbatch.model.Person;
import org.springframework.batch.item.ItemProcessor;
// itemprocessor called after item reader and before item writer
// item processor processors the data before writing to the database
// input obj (left), output obj (right)
public class PersonProcessor implements ItemProcessor<Person, Person> {


    @Override
    public Person process(Person person) throws Exception {
//        System.out.println("Processing data to upper-case");
//        int age = person.getAge();
//        if(age <= 20)
//            person.setName(person.getName() + " "+"Millenial");
//        else
//            person.setName(person.getName() + " " + "Oldies");
        return person;
    }
}
