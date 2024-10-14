/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.e.commerce_platform;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class ECommerce_Platform {
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("\n*********** WELCOME ! ***********\n*********** E-Commerce Platform ***********");
        boolean check = true;
        
        
        ProductList allProductsList = new ProductList();                            // all available products
        int choice= 0;
        final FileHandler storeFileHandler = new FileHandler();
        
        if(!storeFileHandler.readProducts().isEmpty() || storeFileHandler.readProducts() != null){
            allProductsList.setAllFileProducts(storeFileHandler.readProducts());       
        }
        
        do{
            
            System.out.println("\n1. Admin"
                    + "\n2. Store Owner"
                    + "\n3. Customer"
                    + "\n4. Exit"
            );

            System.out.print("\nEnter Choice : ");
            try{
                choice= input.nextInt();
            }
            catch(Exception e){
                System.out.print("\nNumber Only---Enter Again : ");
                input.next();
                continue;
            }
            
            switch(choice){
                case 1:
                    //Skip
                    break;
                
                //Store Owner Input Data
                case 2:
                    
                    
                    boolean checkStoreOwner = true;
                    
                    do{
                        System.out.println("\n*********** Store Owner Menu ***********");
                        System.out.println("\n1. New User"
                                + "\n2. Existing User"
                                + "\n3. Exit"
                        );

                        System.out.print("\nEnter Choice : ");
                        int choiceStoreOwner = 0;
                        
                        try{
                            
                            choiceStoreOwner = input.nextInt();
                            
                        }catch(Exception e){
                           
                           System.out.print("\nNumber Only---Enter Again : ");
                           input.next();
                           continue;
                           
                        }
                        
                        switch(choiceStoreOwner){
                            
                            //New User
                            case 1:
                                StoreOwner newstoreOwner =(StoreOwner) userInput("storeOwner");    
                                if(newstoreOwner != null){
                                    storeFileHandler.writeStoreOwners(newstoreOwner);
                                    newstoreOwner.storeOwnerMenu(newstoreOwner, allProductsList, storeFileHandler);
                                }
                                else{
                                    System.out.println("Store Owner creation was canceled");
                                }
                                break;
                            
                            //Existing User
                            case 2:
                                System.out.println("Enter ID : ");
                                long choiceExisting = 0;
                                boolean checkExisting = true;
                                while(checkExisting){
                                    
                                    try{
                                        choiceExisting = input.nextLong();
                                        checkExisting = false;
                                    }catch(Exception e){
                                        System.out.print("InValid! Number Only");
                                        input.next();
                                    }
                                    
                                }
                                StoreOwner existingStoreOwner = storeFileHandler.getStoreOwnerID(choiceExisting);
                                if(existingStoreOwner != null){
                                    existingStoreOwner.storeOwnerMenu(existingStoreOwner, allProductsList,storeFileHandler);
                                }
                                else{
                                    System.out.println("Store Owner Not Found \nCreate New ");
                                    System.out.println("****************************************************");
                                }
                                break;
                            case 3:
                                checkStoreOwner = false;
                                System.out.println("\n Store Owner Menu exited.....");
                                break;
                                
                            default:
                                
                                System.out.println("\nInValid Choice!---Enter Again : ");
                                break;
                                
                        }
                    }while(checkStoreOwner);
                    
                    break;
                    
               //Customer Input Data
                case 3:
                    Customer customer = (Customer) userInput("customer");
                    if (customer != null) {
                        storeFileHandler.writeCustomers(customer); 
                        customer.customerMenu(customer, allProductsList, storeFileHandler); 
                    } else {
                        System.out.println("Customer creation was canceled.");
                    }
                    break;
                case 4:
                    System.out.println("\n Main Menu exited.....");
                    check=false;
                    break;
                default:
                    System.out.println("\nInValid Choice!---Enter Again : ");
                    break;
            }
        }while(check);
        
    }
    
    // Taking new User Input
    public static Person userInput(String userType){
        Person newUser = null;
        boolean checkSO1=true;
            do{
                try{

                    System.out.print("Enter ID : ");
                    Long ID = input.nextLong();

                    input.nextLine();

                    System.out.print("Enter Name : ");
                    String Name = input.nextLine();

                    System.out.print("Enter Email : ");
                    String Email = input.nextLine();

                    System.out.print("Enter Adress : ");
                    String Adress = input.nextLine();

                    System.out.print("Enter Phone Number : ");
                    Long PN = input.nextLong();
                    
                if(userType.equalsIgnoreCase("storeOwner")){
                    
                    newUser = new StoreOwner(Name, Email, PN, Adress, ID);
                    System.out.println("\nStore Owner " + Name + " created.");
                    checkSO1 = false;
                    
                }
                else if(userType.equalsIgnoreCase("customer")){
                   
                    newUser = new Customer(ID, Name, Email, Adress ,PN );
                    System.out.println("\nCustomer " + Name + " created.");
                    checkSO1 = false;
                
                }
                else{
                    System.out.println("Error Occured");
                }

                }
                catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    input.nextLine();  // Clear the buffer

                    System.out.println("Want to exit then enter 'YES' ");
                    String newOwnerChoice = input.nextLine();

                    if (newOwnerChoice.equalsIgnoreCase("yes")) {
                        checkSO1 = false;
                        break;
                    }
                }
            }while(checkSO1);
            
            return newUser;
    }
}
