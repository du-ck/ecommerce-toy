package kr.co.ecommerce.toy.infra.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    /**
     * deleted = false 인 상품들을 Option 정보와 함께 모두 조회한다.
     * @return
     */
    @Query("select p from ProductEntity p join fetch p.productOption " +
            "where p.deleted = false ")
    List<ProductEntity> findAllWithOptions();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id = :productId")
    Optional<ProductEntity> findByIdWithLock(@Param("productId") long productId);
}
