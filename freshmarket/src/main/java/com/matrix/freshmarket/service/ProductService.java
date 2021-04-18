package com.matrix.freshmarket.service;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findNotNulltop4(){
        List<ProductEntity> products=
                productRepository.findNotNulltop4();
        return products;
    }




    public List<ProductEntity> findtop8(){
        List<ProductEntity> products=
                productRepository.findbytop8();
        return products;
    }



    public void saveProduct (ProductEntity productEntity) {
        productRepository.save(productEntity);
    }


}
