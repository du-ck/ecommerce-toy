package kr.co.ecommerce.toy.domain.order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> save(Order orderInfo);
}
