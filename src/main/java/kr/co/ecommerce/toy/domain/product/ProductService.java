package kr.co.ecommerce.toy.domain.product;

import kr.co.ecommerce.toy.domain.order.OrderItem;
import kr.co.ecommerce.toy.support.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductInventoryRepository productInventoryRepository;

    /**
     * 전체 상품 리스트 조회 (파라미터 X)
     * 상품 리스트 (상품번호 + 상품명 + 판매가격 + 재고수량)
     */
    public List<Product> getProducts() throws Exception {
        List<Product> result = productRepository.findAll();
        if (CollectionUtils.isEmpty(result)) {
            //throw Exception을 하지만,
            //success = true 로 보낸다.
            throw new ResourceNotFoundException("상품을 찾을 수 없습니다");
        }
        return result;
    }

    /**
     * 해당 productId 를 가진 product 조회
     */
    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }

    /**
     * 해당 productId 를 가진 product 조회 + Lock 획득
     */
    public Optional<Product> getProductWithLock(long productId) {
        return productRepository.findByIdWithLock(productId);
    }

    public boolean decreaseInventory(OrderItem item) {
        Optional<Product> product = getProduct(item.getProductId());
        long productOptionId = product.get().getProductOption().getId();

        Optional<ProductInventory> findInventory = productInventoryRepository.findByProductOptionId(productOptionId);
        if (findInventory.isPresent()) {
            //재고 감소 처리
            ProductInventory inventory = findInventory.get();
            inventory.decreaseInventory(item.getCount());

            Optional<ProductInventory> saveInventory = productInventoryRepository.save(inventory);
            if (!saveInventory.isPresent()) {
                return false;
            }
        } else {
            return false;
        }
        //모두 재고처리 통과 (true)
        return true;
    }
}
