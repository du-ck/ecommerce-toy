package kr.co.ecommerce.toy.infra.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductInventoryJpaRepository extends JpaRepository<ProductInventoryEntity, Long> {

    Optional<ProductInventoryEntity> findByProductOptionId(long productOptionId);
}
