package com.mycompany.e.commerce_platform;

import java.util.ArrayList;

public class Order {
    private long orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalCost;
    private Payment payment;
    private Delivery delivery;
    private String orderDate;

    public Order(long id,Customer customer, ArrayList<Product> products, String orderDate) {
        this.orderId = id;
        this.customer = customer;
        this.products = products; 
//        this.totalCost = totalPrice;
        this.payment = null; 
        this.delivery = null; 
        this.orderDate = orderDate;
        
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        totalCost += product.getPrice();
    }

    public void removeProduct(Product product) {
        if (products != null) {
            products.remove(product);
            totalCost -= product.getPrice();
        }
    }

    public void updateTotalCost() {
        totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer.getName() +
                ", products=" + products +
                ", totalCost=" + totalCost +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}