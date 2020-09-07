package com.orderManagementService.model;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
/*
@Getter
@Setter*/
@Entity
@Table(name="OrderDetails")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;

    @NotBlank
    String customerName;

    Date orderDate;

    @NotBlank
    String shippingAddress;

    @NotNull
    int orderItems;

    @NotNull
    int total;


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setOrderItems(int orderItems) {
        this.orderItems = orderItems;
    }

    public int getOrderItems() {
        return orderItems;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
