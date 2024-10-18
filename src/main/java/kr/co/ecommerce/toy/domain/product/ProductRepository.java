package kr.co.ecommerce.toy.domain.product;


import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(long productId);
    Optional<Product> findByIdWithLock(long productId);
}
