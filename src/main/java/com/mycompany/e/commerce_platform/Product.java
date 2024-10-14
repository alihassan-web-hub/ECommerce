/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

import java.util.List;

/**
 *
 * @author HP
 */
public class Product {
    
    //attributes
    private long productID;
    private String productName;
    private double price;
    private int stockQuantity;
    private List<Review> reviews;
    private Store store;
    
    //constructor
    public Product(long productID, String productName, double price, int stockQuantity, Store store) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.store = store;
    }
    
    
    //getter & setter
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    //method
//    public void updateStock(int value){
//        this.stockQuantity -= value;
//    }
    
    public void addReview(){
        
    }
    
    public void getProductDetails(){
        
    }

}
