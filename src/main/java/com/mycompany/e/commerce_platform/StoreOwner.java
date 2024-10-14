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
public class StoreOwner extends Person{
    
    //attributes
    
    private long storeOwnerID;
    private ArrayList<Store> store = new ArrayList<>();       //tocheck******************
    
    //constructor
    public StoreOwner(String name, String email, Long phoneNumber, String adress, long storeOwnerID) {
        
        super(name, email, phoneNumber, adress);
        this.storeOwnerID= storeOwnerID;
    }
    
    //getter & setter
    public long getStoreOwnerID() {
        return storeOwnerID;
    }

    public void setStoreOwnerID(long storeOwnerID) {
        this.storeOwnerID = storeOwnerID;
    }

    public ArrayList<Store> getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store.add(store);
    }
    
    // for file handing which is return stores
    public void setStoreOwnerStore(ArrayList<Store> store){
        this.store = store;
    }

    // intializing store file handler
//    FileHandler aaFileHandler = new FileHandler();
    //method Store Owner Menu
    public void storeOwnerMenu(StoreOwner storeOwner, ProductList allProductsList, FileHandler aaFileHandler) throws IOException{
        System.out.println("\n*********** Store Menu ***********");
        
        boolean checkStoreMenu = true;
        int storeMenuChoice =0;            
                    do{
                        System.out.println("1. Create New Store"+
                            "\n2. Manage Existing Store(s)"+
                            "\n3. View All Stores"+ 
                            "\n4. Delete Existing Store"+
                            "\n5. Exit"+
                            "\n Enter Choice : ");
                        try{
                            
                           storeMenuChoice = input.nextInt();
                           
                        }catch(Exception e){
                           
                           System.out.print("InValid! Number Only. ");
                           input.next();
                           continue;
                           
                        }
                        
                        switch(storeMenuChoice){
                            case 1:
                                addNewStore(storeOwner, aaFileHandler);
                                break;
                            case 2:
                                manageExistingStore(storeOwner, allProductsList, aaFileHandler);
                                break;
                            case 3:
                                viewAllStore(storeOwner, aaFileHandler);
                                break;
                            case 4:
                                deleteExistingStore(storeOwner,aaFileHandler);
                                break;
                            case 5:
                                System.out.println("\n Store Menu exited.....");
                                checkStoreMenu = false;
                                break;    
                            default:
                                System.out.print("\nInValid Input!");
                                break;
                        }
                    }while(checkStoreMenu);
        }
    
    // Store methods
    public void addNewStore(StoreOwner storeOwner, FileHandler aaFileHandler) throws IOException{
        System.out.println("\n*********** Adding New Store ***********");
        boolean storeCheck = true;
        long storeId =0;
        while(storeCheck){
            System.out.println("Enter Store ID : ");
            
            try{
                storeId = input.nextLong();
                storeCheck = false;
            }
            catch(Exception e){
                System.out.println("InValid! Number Only");
                input.next();
            }
        }
        

        input.nextLine();
        System.out.println("Enter Store Name : ");
        String storeName = input.nextLine();

        System.out.println("Enter Store Location : ");
        String storeLocation = input.nextLine();

        if(storeId != 0 && storeOwner != null && storeName != null && storeLocation != null ){
            Store newStore = new Store(storeId, storeOwner, storeName, storeLocation);
            System.out.println("Store Created ---");
            this.setStore(newStore);
            aaFileHandler.writeStores(newStore);
            storeCheck = false;
        }
        
        System.out.println("\n*****************************************");
        
    }
    
    public void manageExistingStore(StoreOwner storeOwner, ProductList allProductsList, FileHandler storeFileHandler) throws IOException{
        System.out.println("*********** Existing Store (s) ***********");
        ArrayList<Store> stores = storeFileHandler.getStores(storeOwner.getStoreOwnerID());
        storeOwner.setStoreOwnerStore(stores);
        if(!storeOwner.getStore().isEmpty()){
            boolean check = true;
            do{
                
//****************************** Without File Handling (Start) ***************************************************
                //displaying all store name(id)
//                for(int i=0; i<storeOwner.getStore().size() ; i++){
//                    System.out.println("Store Name : " + storeOwner.getStore().get(i).getStoreName() + " ( " + + storeOwner.getStore().get(i).getStoreId() + " ) ");
//                }
//                long existStoreId = 0;
//                boolean existStoreCheck = false;
//                //checking id exception
//                while(!existStoreCheck){
//                    System.out.println("Enter store id : ");
//                    try{
//                       existStoreId = input.nextLong();
//                       existStoreCheck = true;
//                    }
//                    catch(Exception e){
//                        System.out.println("INValid! Number Only");
//                        input.next();
//                    }
//                }
//                
//                boolean storeFound = false;
//                
//                for(int i=0; i<storeOwner.getStore().size() ; i++){
//                    if(existStoreId ==  storeOwner.getStore().get(i).getStoreId()){
//                        System.out.println("Store Exists");
//                        storeOwner.getStore().get(i).storeMenu(storeOwner.getStore().get(i), allProductsList);
//                        check = false;
//                        storeFound = true; // Store is found and handled
//                        break; 
//                    }
//                }
//         
//****************************** Without File Handling (End) ***************************************************
                
                for(int i=0; i<storeOwner.getStore().size() ; i++){
                    System.out.println("Store Name : " + storeOwner.getStore().get(i).getStoreName() + " ( " + + storeOwner.getStore().get(i).getStoreId() + " ) ");
                }
                
                long existStoreId = 0;
                boolean existStoreCheck = false;
                while(!existStoreCheck){
                    System.out.println("Enter store id : ");
                    try{
                       existStoreId = input.nextLong();
                       existStoreCheck = true;
                    }
                    catch(Exception e){
                        System.out.println("INValid! Number Only");
                        input.next();
                    }
                }
                
                boolean storeFound = false;
                
                for(int i=0; i<storeOwner.getStore().size() ; i++){
                    if(existStoreId ==  storeOwner.getStore().get(i).getStoreId()){
                        System.out.println("Store Exists");
                        storeOwner.getStore().get(i).storeMenu(storeOwner.getStore().get(i), allProductsList, storeFileHandler);
                        check = false;
                        storeFound = true; // Store is found and handled
                        break; 
                    }
                }
                if(!storeFound){
                    System.out.println("Store Doesn't Exist");
                    System.out.println("Try Again ---- > Yes or No");
                    input.nextLine();
                    String choiceCheck = input.nextLine();
                    if(!choiceCheck.equalsIgnoreCase("Yes")){
                        check = false;
                    }
                }
                else{
                    check = false;
                }
            }while(check);
            
        }
        else{
            System.out.println("No Store Available.");
        }
        System.out.println("\n*****************************************");
    }
    
    
    
    public void deleteExistingStore(StoreOwner storeOwner, FileHandler storeFileHandler) throws IOException{
        System.out.println("*********** Deleting Existing Store ***********");
        ArrayList<Store> stores = storeFileHandler.getStores(storeOwner.getStoreOwnerID());
        storeOwner.setStoreOwnerStore(stores);
        
        if(!storeOwner.getStore().isEmpty()){
            boolean check = true;
            do{
                //displaying all store name(id)
                for(int i=0; i<storeOwner.getStore().size() ; i++){
                    System.out.println("Store Name : " + storeOwner.getStore().get(i).getStoreName() + " ( " + + storeOwner.getStore().get(i).getStoreId() + " ) ");
                }
                long existStoreId = 0;
                boolean existStoreCheck = false;
                //checking id exception
                while(!existStoreCheck){
                    System.out.println("Enter store id : ");
                    try{
                       existStoreId = input.nextLong();
                       existStoreCheck = true;
                    }
                    catch(Exception e){
                        System.out.println("INValid Format --- Number Only");
                        input.next();
                    }
                }
                
                boolean storeFound = false;
                
                for(int i=0; i<storeOwner.getStore().size() ; i++){
                    if(existStoreId ==  storeOwner.getStore().get(i).getStoreId()){
                        System.out.println("Store Exists");
                        System.out.print("Do You want To Delete : ");
                        input.nextLine();
                        String delStore = input.nextLine();
                        if(delStore.equalsIgnoreCase("yes")){
                            storeFileHandler.deleteStore(existStoreId);
                            storeOwner.store.remove(i);
                            check = false;
                        }
                        else{
                            System.out.print("Deletion Cancel");
                        }
                        storeFound = true; // Store is found and handled
                        break;
                    }
                }
                
                if(!storeFound){
                    System.out.println("Store Doesn't Exist");
                    System.out.println("Try Again ---- > Yes or No");
                    input.nextLine();
                    String choiceCheck = input.nextLine();
                    if(!choiceCheck.equalsIgnoreCase("Yes")){
                        check = false;
                    }
                }
                else{
                    check = false;
                }
            }while(check);
            
        }
        else{
            System.out.println("No Store Available.");
        }
        
        System.out.println("\n*****************************************");
    }
    
    public void viewAllStore(StoreOwner storeOwner, FileHandler storeFileHandler){
//********************************* Without File Handling (Start) ******************************************
//        System.out.println("*********** Viewing All Store ***********");
//        
//        if(!storeOwner.getStore().isEmpty()){
//            for(int i=0; i<storeOwner.getStore().size() ; i++){
//                
//                System.out.println("Store ID : " + storeOwner.getStore().get(i).getStoreId());
//                System.out.println("Store Name : " + storeOwner.getStore().get(i).getStoreName());
//                System.out.println("Store Location : " + storeOwner.getStore().get(i).getStoreName());
//                System.out.println("Store Owner : " + storeOwner.getStore().get(i).getStoreOwner().getName());
//            
//            }
//        }
//        else{
//            System.out.println("No Store Available.");
//        }
//        System.out.println("\n*****************************************");
//********************************* Without File Handling (End) ******************************************

        System.out.println("*********** Viewing All Store ***********");
        ArrayList<Store> stores = storeFileHandler.getStores(storeOwner.getStoreOwnerID());
        storeOwner.setStoreOwnerStore(stores);
        if(!storeOwner.getStore().isEmpty()){
            for(int i=0; i<storeOwner.getStore().size() ; i++){
                
                System.out.println("Store ID : " + storeOwner.getStore().get(i).getStoreId());
                System.out.println("Store Name : " + storeOwner.getStore().get(i).getStoreName());
                System.out.println("Store Location : " + storeOwner.getStore().get(i).getStoreName());
                System.out.println("Store Owner : " + storeOwner.getStore().get(i).getStoreOwner().getName());
            
            }
        }
        else{
            System.out.println("No Store Available.");
        }
        System.out.println("\n*****************************************");
    }
  
    
}
