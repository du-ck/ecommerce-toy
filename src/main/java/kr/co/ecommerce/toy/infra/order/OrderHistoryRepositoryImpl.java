package kr.co.ecommerce.toy.infra.order;

import kr.co.ecommerce.toy.domain.order.OrderHistory;
import kr.co.ecommerce.toy.domain.order.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderHistoryRepositoryImpl implements OrderHistoryRepository {

    private final OrderHistoryJpaRepository jpaRepository;

    @Override
    public Optional<OrderHistory> save(OrderHistory orderHistoryInfo) {
        Optional<OrderHistoryEntity> entity = Optional.of(jpaRepository.save(OrderHistoryEntity.toEntity(orderHistoryInfo)));
        if (entity.isPresent()) {
            return Optional.of(OrderHistoryEntity.toDomain(entity.get()));
        }
        return Optional.empty();
    }
}
