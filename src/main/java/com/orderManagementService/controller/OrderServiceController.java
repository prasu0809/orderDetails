package com.orderManagementService.controller;

import com.orderManagementService.dao.OrderDetailsDao;
import com.orderManagementService.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/OrderDetails")
public class OrderServiceController {

    @Autowired
    OrderDetailsDao orderDetailsDao;

    // save order details to Database
    @PostMapping("/createOrderDetails")
    public OrderDetails createOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
        return orderDetailsDao.save(orderDetails);

    }

    //to retrieve All order details
    @GetMapping("/allOrderDetails")
    public List<OrderDetails> getAllInformation() {
        return orderDetailsDao.findAll();
    }

    // Retrieve personal information by ID
    @GetMapping("/getOrderInfoByID/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsByID(@PathVariable(value = "id") long id) {
        OrderDetails orderDetails = orderDetailsDao.findone(id);

        if (orderDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderDetails);

    }

}



