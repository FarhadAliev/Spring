package com.matrix.freshmarket.repository;

import com.matrix.freshmarket.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

List<ProductEntity> findAllBySpecialPriceIsNotNull();


    @Query(value = "SELECT * FROM  product"
            +" WHERE special_price Is NULL"+
            "  LIMIT 8 ",
            nativeQuery=true)
   List<ProductEntity> findbytop8();
}
