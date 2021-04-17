package com.matrix.freshmarket.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id ;
    @Column(name = "product_name")
    String productName;
    @Column(name = "product_price")
    BigDecimal productPrice;
    @Column(name = "special_price")
    BigDecimal specialPrice;
    @Column(name = "product_category")
    String productCategory;
    @Column(name = "product_img")
    String productImg;
    @Column(name = "product_info")
    String productInfo;
    @Column(name = "product_ingredients")
    String productIngredients;





}
