package com.example.beprojec2.Payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderRequest {
    private int foodid;
    private int quantity;
}
