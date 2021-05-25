package com.matrix.freshmarket.repository;

import com.matrix.freshmarket.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

 @Query(value = "SELECT * FROM product WHERE product_name=:productName", nativeQuery = true )
 ProductEntity findByProductAdd(@Param("productName") String s);

 Optional<ProductEntity> findByProductName(String s);

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


    Page<ProductEntity> findAll(Pageable pageable);


    @Query(value = "select * from product WHERE product_category='Food'", nativeQuery = true)
    Page<ProductEntity> findFood(Pageable pageable);


    @Query(value = "select * from product WHERE product_category='Drink'", nativeQuery = true)
    Page<ProductEntity> findDrink(Pageable pageable);


    @Query(value = "select * from product WHERE product_category='Personal Care'", nativeQuery = true)
    Page<ProductEntity> findPersonalCare(Pageable pageable);


    @Query(value = "select * from product WHERE product_category='Cleaning Supplies'", nativeQuery = true)
    Page<ProductEntity> findCleaningSupplies(Pageable pageable);


     @Query(value = "SELECT * FROM product WHERE product_category = :productCategory and product_price Between :startPrice and :endPrice   " ,nativeQuery = true)
    Page<ProductEntity> findByPriceAndProductCategory(Pageable pageable,@Param("startPrice") String minPrice,
                                  @Param("endPrice")String maxPrice, @Param("productCategory") String productCategory);




 @Query(value = "SELECT * FROM product WHERE product_price Between :startPrice and :endPrice   " ,nativeQuery = true)
 Page<ProductEntity> findAllByPriceAndProductCategory(Pageable pageable,@Param("startPrice") String minPrice,
                                                   @Param("endPrice")String maxPrice);









 @Query(value = "SELECT * FROM product WHERE product_price Between :startPrice and :endPrice", nativeQuery = true)
 Page<ProductEntity> findAllPrice(Pageable pageable,@Param("startPrice") String minPrice,
                                  @Param("endPrice")String maxPrice);


 @Query(value = "SELECT * FROM product WHERE product_category='Food' and product_price BETWEEN :startPrice and :endPrice", nativeQuery = true)
 Page<ProductEntity> findFoodPrice(Pageable pageable,@Param("startPrice") String minPrice,
                              @Param("endPrice")String maxPrice);


 @Query(value = "SELECT * FROM product WHERE product_category='Drink' and product_price BETWEEN :startPrice and :endPrice", nativeQuery = true)
 Page<ProductEntity> findDrinkPrice(Pageable pageable,@Param("startPrice") String minPrice,
                               @Param("endPrice")String maxPrice);


 @Query(value = "SELECT * FROM product WHERE product_category='Personal Care' and product_price BETWEEN :startPrice and :endPrice", nativeQuery = true)
 Page<ProductEntity> findPersonalCarePrice(Pageable pageable,@Param("startPrice") String minPrice,
                                      @Param("endPrice")String maxPrice);


 @Query(value = "SELECT * FROM product WHERE product_category='Cleaning Supplies' and product_price BETWEEN :startPrice and :endPrice", nativeQuery = true)
 Page<ProductEntity> findCleaningSuppliesPrice(Pageable pageable,@Param("startPrice") String minPrice,
                                          @Param("endPrice")String maxPrice);

 @Query(value = "SELECT * FROM product WHERE product_name=:productName", nativeQuery = true )
 List<ProductEntity> findbyName(@Param("productName") String productName);

}
