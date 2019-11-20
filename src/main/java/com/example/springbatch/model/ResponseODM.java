package com.example.springbatch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseODM {
    private int id;
    // name of person
    private String firstName;
    // amount requested for loan
    private float amount;
    // yearly income of person
    private float yearlyIncome;
    // returns accepted status in true or false fashion
    private String status;
    // returns the message if the loan was approved or rejected
    private String messages;

    public ResponseODM(String firstName, float amount, float yearlyIncome, String status, String messages) {
        this.firstName = firstName;
        this.amount = amount;
        this.yearlyIncome = yearlyIncome;
        this.status = status;
        this.messages = messages;
    }
}
