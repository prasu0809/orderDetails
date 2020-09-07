package com.orderManagementService.repository;

import com.orderManagementService.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<OrderDetails, Long> {

}
