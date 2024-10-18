package kr.co.ecommerce.toy.support.exception;

public class OutOfStockException extends Exception{

    public OutOfStockException(String item) {
        super(item + " 의 재고가 부족합니다");
    }
}
