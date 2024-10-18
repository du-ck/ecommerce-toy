package kr.co.ecommerce.toy.infra.product;

import jakarta.persistence.*;
import kr.co.ecommerce.toy.domain.product.ProductOption;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "product_option")
public class ProductOptionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false, insertable=false, updatable=false)
    private Long productId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToOne(mappedBy = "productOption", fetch = FetchType.LAZY)
    private ProductInventoryEntity inventory;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public static ProductOption toDomain(ProductOptionEntity entity) {
        return ProductOption.builder()
                .id(entity.id)
                .productId(entity.productId)
                .price(entity.price)
                .inventory(entity.inventory.getInventory())
                .build();
    }
}
