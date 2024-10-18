package kr.co.ecommerce.toy.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    public Optional<Order> saveOrder(Order orderInfo) {
        return orderRepository.save(orderInfo);
    }

    public Optional<OrderHistory> saveOrderHistory(OrderHistory orderHistoryInfo) {
        return orderHistoryRepository.save(orderHistoryInfo);
    }
}
