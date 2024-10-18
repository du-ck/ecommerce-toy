package kr.co.ecommerce.toy.domain.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class Order {
    private Long id;
    private Long userId;
    private List<OrderHistory> orderHistories;
    private Long payPrice;
    private OrderType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 배송비 : 총 주문금액이 5만원 미만인 경우 2,500원 추가
     */
    public void priceCalculate() {
        long totalPrice = 0L;
        for (OrderHistory orderHistory : orderHistories) {
            totalPrice += orderHistory.getProductPrice() * orderHistory.getCount();
        }
        if (totalPrice < 50000) {
            this.payPrice = totalPrice + 2500;
        } else {
            this.payPrice = totalPrice;
        }
    }

    /**
     * 오류 등으로 주문실패 시 status 변경
     */
    public void orderFail() {
        this.status = OrderType.FAIL;
    }

    /**
     * status 완료 처리
     */
    public void orderCompleted() {
        this.status = OrderType.COMPLETED;
    }
}
