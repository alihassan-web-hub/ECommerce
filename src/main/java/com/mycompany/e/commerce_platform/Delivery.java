/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */
public class Delivery {
    private int deliveryId;
//    private String deliveryMethod;
    private String deliveryAddress;
    private double deliveryChargers;
    public Delivery(int deliveryId,String deliveryAddress, double deliveryCharges) {
        this.deliveryId = deliveryId;
//        this.deliveryMethod = deliveryMethod;
        this.deliveryAddress = deliveryAddress;
        this.deliveryChargers = deliveryCharges;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

//    public String getDeliveryMethod() {
//        return deliveryMethod;
//    }
//
//    public void setDeliveryMethod(String deliveryMethod) {
//        this.deliveryMethod = deliveryMethod;
//    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getDeliveryChargers() {
        return deliveryChargers;
    }

    public void setDeliveryChargers(double deliveryChargers) {
        this.deliveryChargers = deliveryChargers;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
//                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
