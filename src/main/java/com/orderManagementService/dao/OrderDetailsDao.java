package com.orderManagementService.dao;
import com.orderManagementService.model.OrderDetails;
import com.orderManagementService.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsDao {

    @Autowired
    OrderServiceRepository orderServiceRepository;

    // saves the data to database
    public OrderDetails save(OrderDetails orderDetails) {
        return orderServiceRepository.save(orderDetails);
    }

    // it retrieves all the Information from the database
    public List<OrderDetails> findAll(){
        return orderServiceRepository.findAll();

    }

    // retrieves data based on id
    public OrderDetails findone(long id) {
        return orderServiceRepository.findOne(id);

    }

    // deletes the data from the database
    public void delete(OrderDetails orderDetails) {
        orderServiceRepository.delete(orderDetails);
    }



}

