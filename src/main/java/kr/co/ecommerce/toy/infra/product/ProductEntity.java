package kr.co.ecommerce.toy.infra.product;

import jakarta.persistence.*;
import kr.co.ecommerce.toy.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductOptionEntity productOption;

    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.id)
                .name(entity.name)
                .categoryId(entity.categoryId)
                .productOption(ProductOptionEntity.toDomain(entity.productOption))
                .createdAt(entity.createdAt)
                .build();
    }

    public static List<Product> toDomainList(List<ProductEntity> entities) {
        return entities.stream().map(m -> toDomain(m)).toList();
    }
}
