package com.matrix.freshmarket.service;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    public List<ProductEntity> getProduct(String direction ,String property){

        Sort sort = direction.equals("asc")
                ?Sort.by(Sort.Direction.ASC,property)
                :Sort.by(Sort.Direction.DESC,property);

        List<ProductEntity> products=
                productRepository.findAll(sort);
        return products;
    }


    public Page<ProductEntity> getProduct(Pageable pageable){

        return productRepository.findAll(pageable);
    }


    public List<ProductEntity> getProductAll(){

        return productRepository.findAll();
    }

    public Page<ProductEntity> getFood(Pageable pageable){

        return productRepository.findFood(pageable);
    }



    public void saveProduct (ProductEntity productEntity) {
        productRepository.save(productEntity);
    }


}
