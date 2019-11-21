package com.example.productbatch.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseDiscount {
    // properties
    private int id;
    private double discount;
    private String message;

    public ResponseDiscount(double discount, String message) {
        this.discount = discount;
        this.message = message;
    }
}
