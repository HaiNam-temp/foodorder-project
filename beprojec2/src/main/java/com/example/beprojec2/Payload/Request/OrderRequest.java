package com.example.beprojec2.Payload.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderRequest {
    private int accountid;
    List<FoodOrderRequest> foodOrderRequestList;
}
