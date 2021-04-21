package com.matrix.freshmarket.repository;

import com.matrix.freshmarket.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT * FROM  product"
            +" WHERE special_price IS NOT NULL"+
            "  LIMIT 6 ",
            nativeQuery=true)
List<ProductEntity> findNotNulltop4();


    @Query(value = "SELECT * FROM  product"
            +" WHERE special_price Is NULL"+
            "  LIMIT 8 ",
            nativeQuery=true)
   List<ProductEntity> findbytop8();

    @Query(value =" SELECT * FROM product WHERE special_price IS NOT NULL GROUP BY id ASC LIMIT 4 OFFSET 0;", nativeQuery=true)
    List<ProductEntity> findFirstElement();

}