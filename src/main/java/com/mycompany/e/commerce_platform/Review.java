/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */


public class Review {
    
    //attributes
    private long deliveryID;
    private Order order;
    private Store store;
    private String status;
    
    //constructor

    public Review(long deliveryID, Order order, Store store, String status) {
        this.deliveryID = deliveryID;
        this.order = order;
        this.store = store;
        this.status = status;
    }
    
    //getter & setter

    public long getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(long deliveryID) {
        this.deliveryID = deliveryID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    //method
    public void scheduleDelivery(){
        
    }
    public void updateDeliveryStatus(){
        
    }
}
