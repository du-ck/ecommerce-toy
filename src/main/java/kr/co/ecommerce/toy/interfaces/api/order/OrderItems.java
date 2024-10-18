package kr.co.ecommerce.toy.interfaces.api.order;

import kr.co.ecommerce.toy.domain.order.Order;
import kr.co.ecommerce.toy.domain.order.OrderItem;
import lombok.*;

import java.util.List;

public class OrderItems {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        long userId;
        List<OrderItem> orderItems;
    }

    @Builder
    @Getter
    public static class Response {
        Order order;
    }
}
