package kr.co.ecommerce.toy.interfaces.api.order;

import kr.co.ecommerce.toy.application.facade.OrderFacade;
import kr.co.ecommerce.toy.domain.order.Order;
import kr.co.ecommerce.toy.interfaces.api.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderFacade orderFacade;

    /**
     * 상품을 주문한다.
     * req : 주문할 아이템 리스트 ( 상품번호 + 주문수량 )
     */
    @PostMapping("/orderItems")
    public ResponseEntity<ResponseData> orderItems(@RequestBody OrderItems.Request req) throws Exception {

        Optional<Order> order = orderFacade.order(req.getOrderItems(), req.userId);

        OrderItems.Response response = OrderItems.Response.builder()
                .order(order.get())
                .build();

        return new ResponseEntity<>(ResponseData.builder()
                .isSuccess(true)
                .code("200")
                .data(response)
                .build(), HttpStatus.OK);
    }
}
