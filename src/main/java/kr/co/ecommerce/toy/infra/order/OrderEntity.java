package kr.co.ecommerce.toy.infra.order;

import jakarta.persistence.*;
import kr.co.ecommerce.toy.domain.order.Order;
import kr.co.ecommerce.toy.domain.order.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"order\"")
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "pay_price", nullable = false)
    private Long payPrice;

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderHistoryEntity> orderHistoryList;

    public static OrderEntity toEntity(Order domain) {
        return OrderEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .payPrice(domain.getPayPrice())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static Order toDomain(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                //.orderHistories(OrderHistoryEntity.toDomainList(entity.getOrderHistoryList()))
                .payPrice(entity.getPayPrice())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
