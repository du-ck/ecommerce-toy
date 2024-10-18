package kr.co.ecommerce.toy.domain.product;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductOption {
    private Long id;
    private Long productId;
    private Long price;
    private Long inventory;
}
