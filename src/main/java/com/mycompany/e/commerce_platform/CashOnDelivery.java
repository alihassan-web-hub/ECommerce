/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

import java.util.Date;

/**
 *
 * @author HP
 */
public class CashOnDelivery extends Payment{
    public CashOnDelivery(int paymentId, double amount, Date paymentDate) {
        super(paymentId, "Cash On Delivery", amount, paymentDate);
    }
}
