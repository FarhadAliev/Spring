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





    public Page<ProductEntity> getProduct(Pageable pageable,String direction,String property){

        if(property.equals("food")){
            return productRepository.findFood(pageable);
        }else if(property.equals("drink")){
            return  productRepository.findDrink(pageable);
        }else if(property.equals("personalCare")){
            return  productRepository.findPersonalCare(pageable);
        }else if(property.equals("cleaningSupplies")){
        return  productRepository.findCleaningSupplies(pageable);
    }


        return productRepository.findAll(pageable);
    }






    public Page<ProductEntity> getFood(Pageable pageable){

        return productRepository.findFood(pageable);
    }



    public void saveProduct (ProductEntity productEntity) {
        productRepository.save(productEntity);
    }


}
