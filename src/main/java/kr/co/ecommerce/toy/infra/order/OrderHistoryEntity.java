package kr.co.ecommerce.toy.infra.order;

import jakarta.persistence.*;
import kr.co.ecommerce.toy.domain.order.OrderHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_history")
public class OrderHistoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Long productPrice;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "inventory", nullable = false)
    private Long inventory;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static OrderHistory toDomain(OrderHistoryEntity entity) {
        return OrderHistory.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .productPrice(entity.getProductPrice())
                .count(entity.getCount())
                .inventory(entity.getInventory())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static List<OrderHistory> toDomainList(List<OrderHistoryEntity> entities) {
        return entities.stream().map(m -> toDomain(m)).toList();
    }

    public static OrderHistoryEntity toEntity(OrderHistory domain) {
        return OrderHistoryEntity.builder()
                .id(domain.getId())
                .orderId(domain.getOrderId())
                .productId(domain.getProductId())
                .productName(domain.getProductName())
                .productPrice(domain.getProductPrice())
                .count(domain.getCount())
                .inventory(domain.getInventory())
                .createdAt(domain.getCreatedAt())
                .build();
    }
}
