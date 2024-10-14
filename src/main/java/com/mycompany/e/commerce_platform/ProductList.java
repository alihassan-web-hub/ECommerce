/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */
import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> productList;
    
    ProductList(){
        this.productList = new ArrayList<>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(Product product) {
        this.productList.add(product);
    }
    
    public void setAllFileProducts(ArrayList<Product> products){
        this.productList = products;
    }
    
}
