package kr.co.ecommerce.toy.domain.product;


import java.util.Optional;

public interface ProductInventoryRepository {
    Optional<ProductInventory> findByProductOptionId(long productOptionId);

    Optional<ProductInventory> save(ProductInventory inventory);
}
