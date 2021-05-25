package com.matrix.freshmarket.dao;

import com.matrix.freshmarket.entity.ProductEntity;

import java.io.Serializable;

public class Cart implements Serializable {

    private ProductEntity productEntity;
    private  Long quantity;


    public Cart() {
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public Cart(ProductEntity productEntity, long quantity) {
        this.productEntity = productEntity;
        this.quantity = quantity;
    }
}
