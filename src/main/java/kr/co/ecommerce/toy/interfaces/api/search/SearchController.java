package kr.co.ecommerce.toy.interfaces.api.search;

import kr.co.ecommerce.toy.application.facade.ProductFacade;
import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.interfaces.api.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final ProductFacade productFacade;

    /**
     * 상품을 조회한다.
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<ResponseData> products() throws Exception {
        List<Product> products = productFacade.getProducts();

        Products.Response response = Products.Response.builder()
                .products(products)
                .build();

        return new ResponseEntity<>(ResponseData.builder()
                .isSuccess(true)
                .code("200")
                .data(response)
                .build(), HttpStatus.OK);
    }
}
