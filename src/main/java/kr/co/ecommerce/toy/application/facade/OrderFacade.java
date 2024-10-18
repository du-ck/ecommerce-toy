package kr.co.ecommerce.toy.application.facade;

import kr.co.ecommerce.toy.domain.order.*;
import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;

    /**
     * 상품 주문 API 를 통해 상품을 주문
     *
     * 주문 시 재고가 부족할 경우 주문이 불가능
     */
    @Transactional
    public Optional<Order> order(List<OrderItem> orderItems, long userId) throws Exception {

        List<OrderHistory> histories = new ArrayList<>();
        boolean isDecreased = false;

        //주문 insert
        Optional<Order> saveOrderInfo = orderService.saveOrder(Order.builder()
                        .userId(userId)
                        .status(OrderType.WAITING)
                        .payPrice(0L)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build());

        if (!saveOrderInfo.isPresent()) {
            return Optional.empty();
        }
        Order order = saveOrderInfo.get();

        for (OrderItem item : orderItems) {
            //재고 확인
            Optional<Product> findOptionalItem = productService.getProductWithLock(item.getProductId());

            if (!findOptionalItem.isPresent()) {
                //없는 product id 일 경우
                throw new IllegalArgumentException(String.format("유효하지 않은 product_id 입니다 [id : %s]", item.getProductId()));
            }

            //유효한 product id 일 경우 재고 체크
            Product findItem = findOptionalItem.get();

            findItem.checkCount(item.getCount());   //재고 확인

            //재고 감소 처리
            isDecreased = productService.decreaseInventory(item);

            //주문내역 생성
            Optional<OrderHistory> history = orderService.saveOrderHistory(OrderHistory.builder()
                    .orderId(order.getId())
                    .productId(findItem.getId())
                    .productName(findItem.getName())
                    .productPrice(findItem.getProductOption().getPrice())
                    .count(item.getCount())
                    .inventory(findItem.getProductOption().getInventory() - item.getCount())
                    .createdAt(order.getCreatedAt())
                    .build());

            if (!history.isPresent()) {
                throw new IllegalArgumentException("주문내역 저장 실패");
            }

            histories = order.getOrderHistories();
            if (CollectionUtils.isEmpty(histories)) {
                histories = new ArrayList<>();
            }

            histories.add(history.get());
            order = order.toBuilder()
                    .orderHistories(histories)
                    .build();
        }

        if (!isDecreased) {
            throw new IllegalArgumentException("재고 감소 처리 실패");
        }

        //총 지불금액 및 주문정보 저장 완료 처리
        order.priceCalculate();     // 총 지불금액 계산
        order.orderCompleted();     // status 완료 처리

        Optional<Order> completeSaveOrder = orderService.saveOrder(order);
        if (completeSaveOrder.isPresent()) {
            Order result = completeSaveOrder.get();
            result = result.toBuilder()
                    .orderHistories(histories)
                    .build();
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
