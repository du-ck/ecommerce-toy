package kr.co.ecommerce.toy.interfaces.api.dto;


public record ErrorResponse(
        boolean isSuccess,
        String code,
        String message
) {
}
