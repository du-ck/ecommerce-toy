package kr.co.ecommerce.toy.domain.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OrderHistory {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Long productPrice;
    private Long count;
    private Long inventory;
    private LocalDateTime createdAt;
}
