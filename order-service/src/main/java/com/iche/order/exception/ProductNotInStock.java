package com.iche.order.exception;

public class ProductNotInStock extends RuntimeException{

    public ProductNotInStock(String message){
        super(message);
    }
}
