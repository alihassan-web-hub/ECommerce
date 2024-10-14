/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */
import java.util.Date;

public class OnlinePayment extends Payment {
    
    //attributes
    private long cardNumber;
    private Date expiryDate;
    private String cardholderName;
    
    //constructor

    public OnlinePayment(long cardNumber, Date expiryDate, String cardholderName, int paymentID, double amount, Date paymentDate) {
        super(paymentID, "Online", amount, paymentDate);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardholderName = cardholderName;
    }
    
    //method
    public void validateCard(){
        
    }
}
