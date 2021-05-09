package com.matrix.freshmarket.service;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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





    public Page<ProductEntity> getProduct(Integer page,String direction,String property,String sort){





        if(property.equals("food")){
            return productRepository.findFood(PageRequest.of(page, 8));
        } else  if(property.equals("drink")){
            return productRepository.findDrink(PageRequest.of(page, 8));
        }else  if(property.equals("Personal Care")){
            return productRepository.findPersonalCare(PageRequest.of(page, 8));
        }else  if(property.equals("Cleaning Supplies")){
            return productRepository.findCleaningSupplies(PageRequest.of(page, 8));
        }


        return productRepository.findAll(PageRequest.of(page, 8));

    }



    public Page<ProductEntity> getProductPrice(Integer page,String direction,String property,String min,String max,String sort){




        String findBy ="product_name";
        Sort.Direction order = Sort.Direction.ASC;

        if (sort.equals("Price (low to high)")){
            findBy="product_price";
            order=Sort.Direction.ASC;
        }else if (sort.equals("Price (high to low)")){
            findBy="product_price";
            order=Sort.Direction.DESC;
        }else if (sort.equals("Name Z-A")){
            findBy="product_price";
            order=Sort.Direction.DESC;
        }else if (sort.equals("Name A-Z")){
            findBy="product_name";
            order=Sort.Direction.ASC;
        }


        String first=min.substring(1);
        String second=max.substring(1);

        if(property.equals("food")){
            return productRepository.findFoodPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        } else  if(property.equals("drink")){
            return productRepository.findDrinkPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }else  if(property.equals("Personal Care")){
            return productRepository.findPersonalCarePrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }else  if(property.equals("Cleaning Supplies")){
            return productRepository.findCleaningSuppliesPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }


        return productRepository.findAllPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);

    }





   public Page<ProductEntity> getProducts(Integer page, String productType, String min,String max,String sort){

       String findBy ="product_name";
       Sort.Direction order = Sort.Direction.ASC;

       if (sort.equals("Price (low to high)")){
           findBy="product_price";
           order=Sort.Direction.ASC;
       }else if (sort.equals("Price (high to low)")){
           findBy="product_price";
           order=Sort.Direction.DESC;
       }else if (sort.equals("Name Z-A")){
           findBy="product_price";
           order=Sort.Direction.DESC;
       }else if (sort.equals("Name A-Z")){
           findBy="product_name";
           order=Sort.Direction.ASC;
       }




       String first=min.substring(1);
       String second=max.substring(1);



       if(productType.equals("all")){
           return  productRepository.findAllByPriceAndProductCategory(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
       }

       return  productRepository.findByPriceAndProductCategory(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second, productType);
   }













    public Page<ProductEntity> getProductSort(Integer page, String productType, String min,String max,String sort){




       String findBy = null;
       Sort.Direction order = null;

       if (sort.equals("Price (low to high)")){
           findBy="product_price";
           order=Sort.Direction.ASC;
       }else if (sort.equals("Price (high to low)")){
           findBy="product_price";
           order=Sort.Direction.DESC;
       }else if (sort.equals("Name Z-A")){
           findBy="product_price";
           order=Sort.Direction.DESC;
       }else if (sort.equals("Name A-Z")){
           findBy="product_name";
           order=Sort.Direction.ASC;
       }


        String first=min.substring(1);
        String second=max.substring(1);



        if(productType.equals("all")){
            return  productRepository.findAllByPriceAndProductCategory(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }

        return  productRepository.findByPriceAndProductCategory(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second, productType);
    }



    public Page<ProductEntity> getFood(Pageable pageable){

        return productRepository.findFood(pageable);
    }



    public void saveProduct (ProductEntity productEntity) {
        productRepository.save(productEntity);
    }










   public List<ProductEntity> findbyName(String productName){
        return productRepository.findbyName(productName);
   }








    public Page<ProductEntity> getProductSortDropDown(Integer page, String direction, String productType, String min, String max, String sort){




        String findBy = null;
        Sort.Direction order = null;

        if (sort.equals("Price (low to high)")){
            findBy="product_price";
            order=Sort.Direction.ASC;
        }else if (sort.equals("Price (high to low)")){
            findBy="product_price";
            order=Sort.Direction.DESC;
        }else if (sort.equals("Name Z-A")){
            findBy="product_price";
            order=Sort.Direction.DESC;
        }else if (sort.equals("Name A-Z")){
            findBy="product_name";
            order=Sort.Direction.ASC;
        }


        String first=min.substring(1);
        String second=max.substring(1);




        if(productType.equals("food")){
            return productRepository.findFoodPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        } else  if(productType.equals("drink")){
            return productRepository.findDrinkPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }else  if(productType.equals("Personal Care")){
            return productRepository.findPersonalCarePrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }else  if(productType.equals("Cleaning Supplies")){
            return productRepository.findCleaningSuppliesPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);
        }


        return productRepository.findAllPrice(PageRequest.of(page, 8,Sort.by(order,findBy)),first,second);




    }
}
