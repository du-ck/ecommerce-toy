package kr.co.ecommerce.toy.interfaces;

import kr.co.ecommerce.toy.application.facade.ProductFacade;
import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductOption;

import kr.co.ecommerce.toy.interfaces.api.search.SearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductFacade productFacade;

    @Test
    void products() throws Exception {
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

        given(productFacade.getProducts())
                .willReturn(products);

        mockMvc.perform(get("/api/v1/search/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.data.products").exists())
                .andExpect(jsonPath("$.data.products[*].id").exists())
                .andExpect(jsonPath("$.data.products[*].name").exists())
                .andExpect(jsonPath("$.data.products[*].categoryId").exists())
                .andExpect(jsonPath("$.data.products[*].productOption").exists())
                .andExpect(jsonPath("$.data.products[*].createdAt").exists());
    }
}
