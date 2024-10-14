/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

/**
 *
 * @author HP
 */
import static com.mycompany.e.commerce_platform.ECommerce_Platform.input;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    //attributes
    private ArrayList<Product> products;
    private double totalPrice;
    private Customer customer;
 
    //constructor

    public Cart(Customer customer) {
        this.totalPrice = 0;
        this.products = new ArrayList<>();
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    //method
    public void addProduct(Product product, FileHandler fileHandler) throws IOException{
        
        
        boolean check = false;
        while(!check){
            System.out.print("Enter Product Quantity");
                while(!input.hasNextInt()){
                    System.out.println("InValid! Input");
                    input.next();
                }
            int pQuantity = input.nextInt();
            if(pQuantity <= product.getStockQuantity()){
                this.products.add(product);
                this.totalPrice += (product.getPrice() * pQuantity);
//                product.updateStock(pQuantity);
                int updateQuantity = product.getStockQuantity() - pQuantity;
                product.setStockQuantity(updateQuantity);
                System.out.println("Updated stock quantity: " + product.getStockQuantity() + "----" + updateQuantity);
                fileHandler.deleteProduct(product.getProductID());
                fileHandler.writeProducts(product);
                check = true;
            }
            else{
                System.out.println("Out of Stock");
                System.out.println("Enter Again --- > \'YES\'");
                input.nextLine();
                String response = input.nextLine();
                if(response.equalsIgnoreCase("Yes")){
                    System.out.print("");
                }
                else{
                    System.out.print("Procedure End");
                    check = true;
                }
            }
        }
        
    }
    public void removeProduct(ProductList allProductsList, FileHandler fileHandler) throws IOException {
        System.out.println("Enter product Id");
        while (!input.hasNextLong()) {
            System.out.println("Invalid! Input");
            input.next();  
        }
        long productID = input.nextLong();

        boolean productFound = false;  

        for (int i = 0; i < products.size(); i++) {
            Product cartProduct = products.get(i);

         
            if (productID == cartProduct.getProductID()) {
                productFound = true;

                
                for (Product allProduct : allProductsList.getProductList()) {
                    if (allProduct.getProductID() == productID) {
                        System.out.println(allProduct.getStockQuantity()+ " ," +  cartProduct.getStockQuantity());
                        int updatedQuantity = allProduct.getStockQuantity() + cartProduct.getStockQuantity();
                        allProduct.setStockQuantity(updatedQuantity);
                        System.out.println("Updated " + updatedQuantity);
                        ///Need to be change on file handling of cart because it wrongly update the product file handling
                        fileHandler.deleteProduct(allProduct.getProductID());
                        fileHandler.writeProducts(allProduct);
                        break;
                    }
                }

                this.totalPrice -= cartProduct.getPrice();
                products.remove(i); 
                
                System.out.println("Product removed successfully.");
                
                break;  
            }
        }

        if (!productFound) {
            System.out.println("Product with the given ID not found in the cart.");
        }
    }

    public void emptyCart(){
        this.products.clear();
        this.totalPrice = 0.0;
    }
    public void calculateTotalPrice(){
        System.out.println("Total Price : " + this.totalPrice);
    }
    
}
