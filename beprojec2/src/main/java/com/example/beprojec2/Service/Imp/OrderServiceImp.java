package com.example.beprojec2.Service.Imp;

import com.example.beprojec2.Payload.Request.OrderRequest;
import com.example.beprojec2.Payload.ResponData;

public interface OrderServiceImp {
    ResponData createOrder(String username, OrderRequest orderRequest);
    ResponData deleteOrder(int orderId);
}
