package kr.co.ecommerce.toy.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long productId;
    private Long count;
}
