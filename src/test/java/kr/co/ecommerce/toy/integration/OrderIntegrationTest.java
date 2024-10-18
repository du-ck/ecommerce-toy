package kr.co.ecommerce.toy.integration;

import kr.co.ecommerce.toy.application.facade.OrderFacade;
import kr.co.ecommerce.toy.domain.order.OrderItem;
import kr.co.ecommerce.toy.domain.product.Product;
import kr.co.ecommerce.toy.domain.product.ProductInventory;
import kr.co.ecommerce.toy.domain.product.ProductInventoryRepository;
import kr.co.ecommerce.toy.domain.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class OrderIntegrationTest {

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductInventoryRepository productInventoryRepository;

    @Test
    void 주문_동시성테스트() throws Exception {
        long productId = 648418L;
        long count = 1L;
        long inventory = 5L;

        // 테스트를 위해 재고 추가
        Product product = productRepository.findById(productId).get();
        ProductInventory findInventory = productInventoryRepository.findByProductOptionId(product.getProductOption().getId()).get();
        findInventory = findInventory.toBuilder()
                .inventory(inventory)
                .build();
        productInventoryRepository.save(findInventory);
        //

        List<OrderItem> orderItems = new ArrayList<>();
        List<String> exMsg = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .productId(productId)
                .count(count)
                .build());

        int numThreads = 50;    //쓰레드 개수

        CountDownLatch latch = new CountDownLatch(numThreads);  //쓰레드들을 동시 시작 및 종료를 관리하기 위한 객체
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);


        for (int i = 1; i < numThreads + 1; i++) {
            int userId = i;
            executorService.submit(() -> {
                try {
                    orderFacade.order(orderItems, userId);
                } catch (Exception ex) {
                    exMsg.add(ex.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();  // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown(); //쓰레드 풀 종료

        assertEquals(exMsg.size(), inventory);
    }

    @Test
    void 없는_productId_테스트() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> exMsg = new ArrayList<>();
        orderItems.add(OrderItem.builder()
                .productId(1L)
                .count(1L)
                .build());

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderFacade.order(orderItems, 1L));

        Assertions.assertEquals("유효하지 않은 product_id 입니다 [id : 1]", exception.getMessage());
    }

}
