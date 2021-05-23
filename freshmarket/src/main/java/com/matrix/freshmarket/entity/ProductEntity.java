package com.matrix.freshmarket.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.Size;
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


    public ProductEntity(String productName, BigDecimal productPrice, BigDecimal specialPrice, String productCategory, String productImg, String productInfo, String productIngredients) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.specialPrice = specialPrice;
        this.productCategory = productCategory;
        this.productImg = productImg;
        this.productInfo = productInfo;
        this.productIngredients = productIngredients;
    }
}
