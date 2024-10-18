package kr.co.ecommerce.toy.infra.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
