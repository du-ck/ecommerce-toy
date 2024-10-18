package kr.co.ecommerce.toy.domain.product;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
public class ProductInventory {

    private Long id;
    private Long productOptionId;
    private Long inventory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void decreaseInventory(long count) {
        this.inventory -= count;
    }
}
