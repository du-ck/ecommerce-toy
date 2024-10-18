package kr.co.ecommerce.toy.service;

import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductOption;
import kr.co.ecommerce.toy.domain.product.ProductRepository;
import kr.co.ecommerce.toy.domain.product.ProductService;
import kr.co.ecommerce.toy.support.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void 상품조회() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(Product.builder()
                .id(768848L)
                .name("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종")
                .categoryId(35L)
                .productOption(ProductOption.builder()
                        .productId(768848L)
                        .price(21000L)
                        .inventory(45L)
                        .build())
                .createdAt(LocalDateTime.now())
                .build());

        products.add(Product.builder()
                .id(748943L)
                .name("디오디너리 데일리 세트 (Daily set)")
                .categoryId(21L)
                .productOption(ProductOption.builder()
                        .productId(748943L)
                        .price(19000L)
                        .inventory(89L)
                        .build())
                .createdAt(LocalDateTime.now())
                .build());

        products.add(Product.builder()
                .id(779989L)
                .name("버드와이저 HOME DJing 굿즈 세트")
                .categoryId(10L)
                .productOption(ProductOption.builder()
                        .productId(779989L)
                        .price(35000L)
                        .inventory(43L)
                        .build())
                .createdAt(LocalDateTime.now())
                .build());

        given(productRepository.findAll())
                .willReturn(products);

        List<Product> results = productService.getProducts();
        assertNotNull(results);
        assertEquals(products.size(), results.size());
    }

    @Test
    void 상품조회_결과없음() {
        List<Product> products = new ArrayList<>();
        given(productRepository.findAll())
                .willReturn(products);

        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> productService.getProducts());

        Assertions.assertEquals("상품을 찾을 수 없습니다", exception.getMessage());
    }
}
