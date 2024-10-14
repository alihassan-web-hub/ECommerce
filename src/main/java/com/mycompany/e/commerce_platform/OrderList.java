/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

import java.util.ArrayList;

/**
 *
 * @author HP
 * 
 */


public class OrderList {

    //attribute
    private long orderListID;
    private ArrayList<Order> orders;
    private Customer customer;
    
    //constructor

    public OrderList(long orderListID) {
        this.orderListID = orderListID;
        this.orders = new ArrayList<>();
    }
    
    //getter & setter

    public long getOrderListID() {
        return orderListID;
    }

    public void setOrderListID(long orderListID) {
        this.orderListID = orderListID;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    // method
    public void addOrder(){
        
    }
    
    public void viewAllOrders(){
        
    }
}
