package kr.co.ecommerce.toy.infra.product;

import jakarta.persistence.*;
import kr.co.ecommerce.toy.domain.product.ProductInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_inventory")
public class ProductInventoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_option_id", nullable = false, insertable=false, updatable=false)
    private Long productOptionId;

    @Column(name = "inventory", nullable = false)
    private Long inventory;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOptionEntity productOption;

    public static ProductInventory toDomain(ProductInventoryEntity entity) {
        return ProductInventory.builder()
                .id(entity.getId())
                .productOptionId(entity.getProductOptionId())
                .inventory(entity.getInventory())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static ProductInventoryEntity toEntity(ProductInventory domain) {
        return ProductInventoryEntity.builder()
                .id(domain.getId())
                .productOptionId(domain.getProductOptionId())
                .inventory(domain.getInventory())
                .createdAt(domain.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
