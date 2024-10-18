package kr.co.ecommerce.toy.domain.order;

import java.util.Optional;

public interface OrderHistoryRepository {
    Optional<OrderHistory> save(OrderHistory orderHistoryInfo);
}
