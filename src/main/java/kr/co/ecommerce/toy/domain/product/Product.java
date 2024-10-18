package kr.co.ecommerce.toy.domain.product;

import kr.co.ecommerce.toy.support.exception.OutOfStockException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Product {
    private Long id;
    private String name;
    private Long categoryId;
    private ProductOption productOption;
    private LocalDateTime createdAt;

    /**
     * 재고 체크
     * @param count
     * @throws Exception
     */
    public void checkCount(long count) throws Exception {
        if (this.productOption.getInventory() < count) {
            throw new OutOfStockException(this.name);
        }
    }
}
