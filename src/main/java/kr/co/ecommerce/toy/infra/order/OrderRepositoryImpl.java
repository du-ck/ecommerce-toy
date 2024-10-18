package kr.co.ecommerce.toy.infra.order;

import kr.co.ecommerce.toy.domain.order.Order;
import kr.co.ecommerce.toy.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;

    @Override
    public Optional<Order> save(Order orderInfo) {
        Optional<OrderEntity> orderEntity = Optional.of(jpaRepository.save(OrderEntity.toEntity(orderInfo)));
        if (orderEntity.isPresent()) {
            return Optional.of(OrderEntity.toDomain(orderEntity.get()));
        }
        return Optional.empty();
    }
}
