/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */
public abstract class Person {
    
    //attributes
    private String name;
    private String email;
    private Long phoneNumber;
    private String address;
    
    //constructor
    Person(String name, String email, Long phoneNumber, String adress){
        
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = adress;
        
    }

    //getter & setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        this.name = n;
    }
}
