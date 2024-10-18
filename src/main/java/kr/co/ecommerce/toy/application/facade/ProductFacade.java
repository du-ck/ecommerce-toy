package kr.co.ecommerce.toy.application.facade;

import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductFacade {

    private final ProductService productService;

    /**
     * 전체 상품 리스트 조회 (파라미터 X)
     * 상품 리스트 (상품번호 + 상품명 + 판매가격 + 재고수량)
     */
    public List<Product> getProducts() throws Exception {
        return productService.getProducts();
    }

}
