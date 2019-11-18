package com.example.springbatch.Processor;

import com.example.springbatch.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.batch.item.ItemProcessor;

import java.io.IOException;
import java.util.*;

// itemprocessor called after item reader and before item writer
// item processor processors the data before writing to the database
// input obj (left), output obj (right)
public class PersonProcessor implements ItemProcessor<Person, Person> {

    private final OkHttpClient httpClient = new OkHttpClient();

    @Override
    public Person process(Person person) throws Exception {

        //System.out.println("Processor: "+person.getFirstName());
        boolean showTrace = false;

        if(person.getShowtrace() == 1)
            showTrace = true;

        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"firstName\":\""+person.getFirstName()+"\",")
                .append("\"lastName\":\""+person.getLastName()+"\",")
                .append("\"birthDate\":\""+person.getBirthDate()+"\",")
                .append("\"bankruptcyDate\":\""+person.getBankruptcyDate()+"\",")
                .append("\"bankruptcyReason\":\""+person.getBankruptcyReason()+"\",")
                .append("\"bankruptcyChapter\":\""+person.getBankruptcyChapter()+"\",")
                .append("\"startDate\":\""+person.getStartDate()+"\",")
                .append("\"SSNCode\":\""+person.getSSNCode()+"\",")
                .append("\"zipCode\":\""+person.getZipCode()+"\",")

                .append("\"server\":\""+person.getServer()+"\",")
                .append("\"ruleset\":\""+person.getRuleset()+"\",")
                .append("\"user\":\""+person.get_user()+"\",")
                .append("\"password\":\""+person.getPassword()+"\",")


                .append("\"yearly-income\":"+person.getYearlyIncome()+",")
                .append("\"credit-score\":"+person.getCreditScore()+",")
                .append("\"amount\":"+person.getAmount()+",")
                .append("\"duration\":"+person.getDuration()+",")
                .append("\"yearly-interest-rate\":"+person.getYearlyInterestRate()+",")
                .append("\"showTrace\":"+ showTrace)
                .append("}").toString();

       // System.out.println(json);

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("http://localhost:9060/loan-server/validate")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (
                Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body

            String result = response.body().string();

            // substring / map method

            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> map = mapper.readValue(result, Map.class);

            System.out.println("-------start output of result--------------");
            System.out.println("name of person: " + person.getFirstName());
            System.out.println("loan amount requested: " + person.getAmount());
            System.out.println("Yearly Income level : " + person.getYearlyIncome());

            for (Map.Entry<String,Object> entry : map.entrySet()) {
                showData(entry);

           }
            System.out.println("-------end output of result-------");

    } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return person;
    }

    void showData(Map.Entry<String,Object> map) {
        switch(map.getKey()) {
            case "approved":
                System.out.println("Your Loan is : "+(((Boolean)map.getValue()).booleanValue() ? "Accepted":"Denied"));
                break;
            case "messages":
                System.out.println("Remarks : "+map.getValue());
                break;
        }
    }
}
