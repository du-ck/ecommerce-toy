package kr.co.ecommerce.toy.interfaces.api.search;

import kr.co.ecommerce.toy.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class Products {

    public static class Request {

    }

    @Builder
    @Getter
    public static class Response {
        List<Product> products;
    }

}
