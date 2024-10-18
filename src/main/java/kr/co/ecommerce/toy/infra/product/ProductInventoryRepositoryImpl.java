package kr.co.ecommerce.toy.infra.product;

import kr.co.ecommerce.toy.domain.product.ProductInventory;
import kr.co.ecommerce.toy.domain.product.ProductInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductInventoryRepositoryImpl implements ProductInventoryRepository {

    private final ProductInventoryJpaRepository jpaRepository;

    @Override
    public Optional<ProductInventory> findByProductOptionId(long productOptionId) {
        Optional<ProductInventoryEntity> inventory = jpaRepository.findByProductOptionId(productOptionId);
        if (inventory.isPresent()) {
            return Optional.of(ProductInventoryEntity.toDomain(inventory.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProductInventory> save(ProductInventory inventory) {
        Optional<ProductInventoryEntity> entity = Optional.of(jpaRepository.save(ProductInventoryEntity.toEntity(inventory)));
        if (entity.isPresent()) {
            return Optional.of(ProductInventoryEntity.toDomain(entity.get()));
        }
        return Optional.empty();
    }
}
