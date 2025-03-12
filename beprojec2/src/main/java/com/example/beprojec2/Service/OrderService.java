package com.example.beprojec2.Service;

import com.example.beprojec2.Entity.Account;
import com.example.beprojec2.Entity.Food;
import com.example.beprojec2.Entity.Order;
import com.example.beprojec2.Entity.OrderDetail;
import com.example.beprojec2.Exception.ApiException;
import com.example.beprojec2.Payload.Request.FoodOrderRequest;
import com.example.beprojec2.Payload.Request.OrderRequest;
import com.example.beprojec2.Payload.ResponData;
import com.example.beprojec2.Responsitory.AccountReponsitory;
import com.example.beprojec2.Responsitory.FoodResponsitory;
import com.example.beprojec2.Responsitory.OrderDetailRepository;
import com.example.beprojec2.Responsitory.OrderReponsitory;
import com.example.beprojec2.Service.Imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderReponsitory orderReponsitory;
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    FoodResponsitory foodResponsitory;
    @Override
    public ResponData deleteOrder(int orderId) {
        ResponData responData = new ResponData();
        try {
            orderDetailRepository.deleteById(orderId);
            orderReponsitory.deleteById(orderId);
        } catch (RuntimeException e) {
            System.out.println("Error delete orderid "+ e.getMessage());
            throw new RuntimeException(e);
        }
        responData.setDescription("Deleted order successfully");
        return responData;
    }

    @Override
    public ResponData createOrder(String username, OrderRequest orderRequest) {

        Account account =accountReponsitory.findByUsername(username);
        if(account==null){
            throw ApiException.ErrBadCredentials().build();
        }
        ResponData responData = new ResponData();
        Order order = new Order();
        order.setAccount(account);
        order.setOrderdate(new Date());
        order.setStatus("Chờ xác nhận");
        Order neworder= orderReponsitory.save(order);
        for(FoodOrderRequest foodOrderRequest: orderRequest.getFoodOrderRequestList()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(neworder);
            Food food=foodResponsitory.findByFoodid(foodOrderRequest.getFoodid());
            orderDetail.setFood(food);
            orderDetailRepository.save(orderDetail);
        }
        responData.setDescription("Order created successfully");
        return responData;
    }
}
