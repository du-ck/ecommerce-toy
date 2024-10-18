package kr.co.ecommerce.toy.interfaces.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData<T> {
    private boolean isSuccess;
    private String code;
    private T data;
}
