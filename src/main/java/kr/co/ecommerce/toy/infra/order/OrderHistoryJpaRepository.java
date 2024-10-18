package kr.co.ecommerce.toy.infra.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryJpaRepository extends JpaRepository<OrderHistoryEntity, Long> {
}
