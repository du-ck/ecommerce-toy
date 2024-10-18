package kr.co.ecommerce.toy.infra.product;

import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = jpaRepository.findAllWithOptions();
        return ProductEntity.toDomainList(entities);
    }

    @Override
    public Optional<Product> findById(long productId) {
        Optional<ProductEntity> entity = jpaRepository.findById(productId);
        if (entity.isPresent()) {
            return Optional.of(ProductEntity.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findByIdWithLock(long productId) {
        Optional<ProductEntity> entity = jpaRepository.findByIdWithLock(productId);
        if (entity.isPresent()) {
            return Optional.of(ProductEntity.toDomain(entity.get()));
        }
        return Optional.empty();
    }
}
