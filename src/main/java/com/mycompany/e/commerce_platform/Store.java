/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_platform;

import static com.mycompany.e.commerce_platform.ECommerce_Platform.input;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Store {
    
    //attributes
    private long storeId;
    private StoreOwner storeOwner;
    private ArrayList<Product> productList;
    private String storeName;
    private String location;

    //constructor
    public Store(long storeId, StoreOwner storeOwner, String storeName, String location) {
        this.storeId = storeId;
        this.storeOwner = storeOwner;
        this.storeName = storeName;
        this.location = location;
        this.productList = new ArrayList<>();
    }
    
    
    //method
    
     public void storeMenu(Store store , ProductList allProductsList, FileHandler storeFileHandler) throws IOException{
        System.out.println("\n*********** Store Menu ***********");
        
        boolean checkStoreMenu = true;
        int storeMenuChoice =0;            
            do{
                System.out.println("1. Add Product\n" +
                    "2. Remove Products\n" +
                    "3. View Product\n" +
                    "4. Manage Orders\n" +
                    "5. Go Back");
                try{
                   storeMenuChoice = input.nextInt();
                }catch(Exception e){
                   System.out.print("\nNumber Only---Enter Again : ");
                   input.next();
                   continue;
                }

                switch(storeMenuChoice){
                    case 1:
                        addProduct(store, allProductsList, storeFileHandler);
                        break;
                    case 2:
                        removeProduct(store, storeFileHandler);
                        break;
                    case 3:
                        viewProduct(store, storeFileHandler);
                        break;
                    case 4:
                        manageOrders(storeFileHandler);
                        break;
                    case 5:
                        System.out.print("Product Menu Exited......");
                        checkStoreMenu = false;
                        break;    
                    default:
                        System.out.print("\nInValid Input!");
                        break;
                }
            }while(checkStoreMenu);
        }
     
    //add product
    public void addProduct(Store store , ProductList allProductsList, FileHandler storeFileHandler) throws IOException{
        System.out.println("\n*********** Adding New Product ***********");
        
        boolean productCheck = true;
        long productId =0;
        
        while(productCheck){
            System.out.println("Enter Product ID : ");
            try{
                productId = input.nextLong();
                productCheck = false;
            }
            catch(Exception e){
                System.out.println("InValid! Number Only");
                input.next();
            }
        }
        

        input.nextLine();
        System.out.println("Enter Product Name : ");
        String productName = input.nextLine();

        productCheck = true;
        int productQuantity = 0;
        while(productCheck){   
            System.out.println("Enter Product Quantity : ");
            try{
                productQuantity = input.nextInt();
                productCheck = false;
            }catch(Exception e){
                System.out.println("InValid! Number Only");
                input.next();
            }
        }

        productCheck = true;
        double productPrice = 0;
        while(productCheck){   
            System.out.println("Enter Product Price : ");
            try{
                productPrice = input.nextInt();
                productCheck = false;
            }catch(Exception e){
                System.out.println("InValid! Number Only");
                input.next();
            }
        }
        
        if(productId != 0 && productName != null && productQuantity != 0 && productPrice != 0 ){
            Product newProduct = new Product(productId, productName, productPrice, productQuantity, store);
            System.out.println("Product Created ---");
            productList.add(newProduct);
            allProductsList.setProductList(newProduct);
            storeFileHandler.writeProducts(newProduct);
            productCheck = false;
        }
        System.out.println("\n*****************************************");
        
    }
    public void removeProduct(Store store,FileHandler storeFileHandler) throws IOException{
        System.out.println("\n*********** Deleting Product ***********");
        ArrayList<Product> products = storeFileHandler.getProducts(store.getStoreId());
        store.setStoreProducts(products);
        if(!store.getProductList().isEmpty()){
            
            for(int i=0; i<store.getProductList().size() ; i++){
                System.out.println("Product : " + store.getProductList().get(i).getProductName() + " ( " + store.getProductList().get(i).getProductID() + " ) ");
            }
            
            System.out.print("Enter Product Id : ");
            while(!input.hasNextLong()){
                System.out.println("InValid ! Number Only--- Enter Again : ");
                input.next();
            }
            long productChoice = input.nextInt();
            
            for(int i=0; i<store.getProductList().size() ; i++){
                if(productChoice == store.getProductList().get(i).getProductID()){
                    System.out.println("Product Exists");
                    System.out.print("Do You want To Delete : ");
                    input.nextLine();
                    String delStore = input.nextLine();
                    if(delStore.equalsIgnoreCase("yes")){
                        storeFileHandler.deleteProduct(productChoice);
                        productList.remove(store.getProductList().get(i));
                    }
                    else{
                        System.out.print("Deletion Cancel");
                    }
                }
            }
            
        }else{
            System.out.println("No Product Available");
        }
        System.out.println("\n*****************************************");
    }
    
    //displaying products
    public void viewProduct(Store store, FileHandler storeFileHandler){
        ArrayList<Product> products = storeFileHandler.getProducts(store.getStoreId());
        store.setStoreProducts(products);
        if(!store.getProductList().isEmpty()){
            System.out.println("\n*********** Displaying All Product (s) ***********");
            for(int i=0; i<store.getProductList().size() ; i++){

                System.out.println("Product ID : " + store.getProductList().get(i).getProductID());
                System.out.println("Product Name : " + store.getProductList().get(i).getProductName());
                System.out.println("Product Price : " + store.getProductList().get(i).getPrice());
                System.out.println("Product Quantity : " + store.getProductList().get(i).getStockQuantity());
                System.out.println("Product Store : " + store.getProductList().get(i).getStore().getStoreName());

            }
        }else{
             System.out.println("No Product Available");
        }
        System.out.println("\n*****************************************");
    }

    public void manageOrders(FileHandler storeFileHandler) throws IOException{
        System.out.println("**************** Managing Orders ***********************");
        storeFileHandler.manageStoreOrder(this.storeId);
    }

    
    //getter & setter

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    // for file handing which is return products
    public void setStoreProducts(ArrayList<Product> products){
        this.productList = products;
    }
}

