package kr.co.ecommerce.toy.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class User {
    private Long id;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
}
