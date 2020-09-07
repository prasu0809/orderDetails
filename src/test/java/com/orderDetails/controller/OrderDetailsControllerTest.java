package com.orderDetails.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderManagementService.controller.OrderServiceController;
import com.orderManagementService.dao.OrderDetailsDao;
import com.orderManagementService.model.OrderDetails;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsControllerTest {

    @InjectMocks
    OrderServiceController orderServiceController;

    @Mock
    OrderDetailsDao orderDetailsDao;

    ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderServiceController).build();
    }

    // test case - create personalInformation
    @Test
    public void testCreateOrderDetails() throws Exception {
        when(orderDetailsDao.save(any(OrderDetails.class))).thenReturn(getOrderDetails());
        String orderDetails = mapper.writeValueAsString(getOrderDetails());
        MockHttpServletResponse response = mockMvc.perform(post("/OrderDetails/createOrderDetails").contentType("application/json").content(orderDetails))
                .andExpect(status().isOk()).andReturn().getResponse();
        OrderDetails resultOrderDetails = getResultOrderDetails(response);
        assertEquals(getOrderDetails().getCustomerName(), resultOrderDetails.getCustomerName());
        assertEquals(getOrderDetails().getShippingAddress(), resultOrderDetails.getShippingAddress());
        assertEquals(getOrderDetails().getOrderItems(), resultOrderDetails.getOrderItems());
        assertEquals(getOrderDetails().getTotal(), resultOrderDetails.getTotal());
    }


    private OrderDetails getOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(1);
        orderDetails.setCustomerName("Prasanna");
        orderDetails.setShippingAddress("Chile");
        orderDetails.setOrderItems(2);
        orderDetails.setTotal(1000);
        return orderDetails;
    }

    private OrderDetails getResultOrderDetails(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), OrderDetails.class);
    }


    @Test
    public void getOrderDetailsTest() throws Exception {
        when(orderDetailsDao.findone(1)).thenReturn(getOrderDetails());
        MockHttpServletResponse response = mockMvc.perform(get("/OrderDetails/getOrderInfoByID/{id}", 1))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        OrderDetails resultOrderDetails = getResultOrderDetails(response);
        assertEquals(getOrderDetails().getCustomerName(), resultOrderDetails.getCustomerName());
        assertEquals(getOrderDetails().getOrderItems(), resultOrderDetails.getOrderItems());
    }

    @Test
    public void testFindAll() throws Exception {

        List<OrderDetails> orderDetailsArrayList = new ArrayList<OrderDetails>();
        orderDetailsArrayList.add(getOrderDetails());

        when(orderDetailsDao.findAll()).thenReturn(orderDetailsArrayList);
        MockHttpServletResponse response = mockMvc.perform(get("/OrderDetails/allOrderDetails"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<OrderDetails> allOrderDetails = getAllPersonalInfo(response);
        assertEquals(1, allOrderDetails.size());
    }

    private List<OrderDetails> getAllPersonalInfo(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), new TypeReference<List<OrderDetails>>() {
        });
    }
}

