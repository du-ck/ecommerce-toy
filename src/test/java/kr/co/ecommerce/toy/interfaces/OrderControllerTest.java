package kr.co.ecommerce.toy.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ecommerce.toy.application.facade.OrderFacade;
import kr.co.ecommerce.toy.domain.order.Order;
import kr.co.ecommerce.toy.domain.order.OrderHistory;
import kr.co.ecommerce.toy.domain.order.OrderItem;
import kr.co.ecommerce.toy.domain.order.OrderType;
import kr.co.ecommerce.toy.interfaces.api.order.OrderController;
import kr.co.ecommerce.toy.interfaces.api.order.OrderItems;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderFacade orderFacade;

    @Autowired
    private ObjectMapper objMapper;

    @Test
    void orderItems() throws Exception {
        long userId = 1L;

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .productId(1L)
                .count(1L)
                .build());

        OrderItems.Request req = OrderItems.Request.builder()
                .orderItems(orderItems)
                .userId(userId)
                .build();

        Order order = Order.builder()
                .id(31L)
                .userId(userId)
                .payPrice(23500L)
                .status(OrderType.COMPLETED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderHistories(Collections.singletonList(OrderHistory.builder()
                        .id(1L)
                        .orderId(31L)
                        .productId(768848L)
                        .productName("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종")
                        .productPrice(21000L)
                        .count(1L)
                        .inventory(37L)
                        .createdAt(LocalDateTime.now())
                        .build()))
                .build();


        given(orderFacade.order(anyList(), anyLong()))
                .willReturn(Optional.of(order));

        mockMvc.perform(post("/api/v1/order/orderItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.data.order").exists())
                .andExpect(jsonPath("$.data.order.id").exists())
                .andExpect(jsonPath("$.data.order.userId").exists())
                .andExpect(jsonPath("$.data.order.payPrice").exists())
                .andExpect(jsonPath("$.data.order.status").exists())
                .andExpect(jsonPath("$.data.order.createdAt").exists())
                .andExpect(jsonPath("$.data.order.updatedAt").exists())
                .andExpect(jsonPath("$.data.order.orderHistories").exists());
    }
}
