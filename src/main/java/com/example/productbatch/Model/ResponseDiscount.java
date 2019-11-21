package com.example.productbatch.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseDiscount {
    // properties
    private int id;
    private float discount;
    private String message;

    public ResponseDiscount(float discount, String message) {
        this.discount = discount;
        this.message = message;
    }
}
