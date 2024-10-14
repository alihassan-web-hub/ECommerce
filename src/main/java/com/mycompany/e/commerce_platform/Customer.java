package com.mycompany.e.commerce_platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer extends Person {
    
    private long customerID;
    private Cart cart;
    private OrderList orderList;

    public Customer(Long customerID, String name, String email, String address, long phoneNumber) {
        super(name, email, phoneNumber, address);
        this.customerID = customerID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public void customerMenu(Customer customer, ProductList allProductsList, FileHandler fileHandler) throws IOException {
        cart = new Cart(customer);

        System.out.println("\n*********** Customer Menu ***********");
        boolean checkCustomerMenu = true;
        int custMenuChoice = 0;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("1. View Products" +
                    "\n2. Search Products(s)" +
                    "\n3. View Cart" + 
//                    "\n4. Place Order" +
                    "\n4. Exit" +
                    "\n Enter Choice : ");
            boolean check = true;
            while(check){
                try{
                    custMenuChoice = input.nextInt();
                    check = false;
                }catch(Exception e){
                    System.out.println("InValid!---");
                    input.next();
                }
            }
           
            

            switch(custMenuChoice) {
                case 1:
                    viewProducts(allProductsList);
                    break;
                case 2:
                    searchProducts(allProductsList, input,fileHandler);
                    break;
                case 3:
                    viewCart(allProductsList, input, fileHandler);
                    break;
//                case 4:
//                    placeOrder(input,fileHandler);
//                    break;
                case 4:
                    checkCustomerMenu = false;
                    System.out.print("Customer Menu Exited.....");
                    break;    
                default:
                    System.out.print("\nInValid Input!");
                    break;
            }
        } while (checkCustomerMenu);
    }

    public void viewProducts(ProductList allProductsList) {
        System.out.println("********* Displaying All Products Available on platform********");
        for (Product product : allProductsList.getProductList()) {
            System.out.println("\tProduct Name : " + product.getProductName()
                    + "\n\tProduct Price : " + product.getPrice()
                    + "\n\tProduct Quantity Available : " + product.getStockQuantity()
                    + "\n\tProduct Store : " + product.getStore().getStoreName());
            System.out.println("           ****************");
        }
    }

    public void searchProducts(ProductList allProductsList, Scanner input, FileHandler fileHandler) throws IOException {
        System.out.println("********* Searching Products Available on platform******** ");
        System.out.print("Enter product name : ");
        input.nextLine();
        String pName = input.nextLine();
        boolean check = false;
        for (Product product : allProductsList.getProductList()) {
            if (pName.equalsIgnoreCase(product.getProductName())) {
                System.out.println("Product Available");
                System.out.println("Product Name : " + product.getProductName());
                check = true;
                int choiceCustomer = 0;
                boolean checkCustomer = true;

                while (checkCustomer) {
                    System.out.println("\n*********** Displaying Cart Options ***********");
                    System.out.println("1.Add To Cart" + "\n2. View Cart" + "\n3.Back");
                    
                    boolean check1 = true;
                    while(check1){
                        try{
                            choiceCustomer = input.nextInt();
                            check1 = false;
                        }catch(Exception e){
                            System.out.println("InValid!---");
                            input.next();
                        }
                    }
                    switch (choiceCustomer) {
                        case 1:
                            addToCart(product, fileHandler);
                            break;
                        case 2:
                            viewCart(allProductsList, input,fileHandler);
                            break;
                        case 3:
                            checkCustomer = false;
                            break;
                        default:
                            System.out.println("Invalid ! Input");
                            break;
                        
                    }
                }
            } else {
                check = false;
            }
        }
        
        if(check ){
//            System.out.println("Product Available");
        }
        else{
            System.out.println("Product Not Available");
        }
    }

    public void viewCart(ProductList allProductsList, Scanner input, FileHandler fileHandler) throws IOException {
        System.out.println("Cart Total Price : " + cart.getTotalPrice());
        
        if(cart.getProducts().isEmpty()){
            System.out.println("No Product Selected ---- Select Product First");
        }
        else{
            
            for (Product product : cart.getProducts()) {
                System.out.println("Product Name : " + product.getProductName());
            }

            int choiceViewCart = 0;
            boolean checkViewCart = true;

            while (checkViewCart) {
                System.out.println("\n*********** Displaying Order Menu ***********");
                System.out.println("1. Place Order " + "\n2. Remove Product " + "\n3. Go Back");

                choiceViewCart = input.nextInt();

                switch (choiceViewCart) {
                    case 1:
                        placeOrder(input, fileHandler);
                        System.out.println("\n*********** Returning to Main Menu ***********");
                        customerMenu(this, allProductsList, fileHandler);
                        break;
                    case 2:
                        cart.removeProduct(allProductsList, fileHandler);
                        break;
                    case 3:
                        checkViewCart = false;
                        break;
                    default:
                        System.out.println("Invalid ! Input");
                        break;
                }
            }
            
        }
    }

    public void addToCart(Product product, FileHandler fileHandler) throws IOException {
        cart.addProduct(product, fileHandler);
        System.out.println("Product added to cart");
    }

    public void placeOrder(Scanner input,FileHandler fileHandler) throws IOException {
        System.out.println("\n*********** Placing Order ***********");
        try{
            long orderID = (long) (Math.random() * 900) + 100;
            Delivery custDelivery = customerDelivery(input);
            double totalPrice = cart.getTotalPrice() + custDelivery.getDeliveryChargers() ;
            Payment custPayment = customerPayment(input, totalPrice);
        
        
            Order order = new Order(orderID, this, cart.getProducts(),new Date().toString());
        
            order.setTotalCost(totalPrice);

            order.setPayment(custPayment);
            order.setDelivery(custDelivery);

            fileHandler.writeOrder(order);

            System.out.println("Order Information");
            System.out.println(order.toString());
            cart.emptyCart();
            
        }catch(Exception e){
            System.out.println("Some Error Occur while placing order " + e);
        }
           
    }

    public Delivery customerDelivery(Scanner input) {
        int deliveryID = (int) (Math.random() * 900) + 100;
        boolean check = true;
        String deliveryAddress = null;
        System.out.println("\n*********** Displaying Order Delivery ***********");
        do {
            System.out.println("Where To deliver "
                    + "\nYes -- > For Customer Mention Address : " + getAddress()
                    + "\nNo  -- > Enter Separate Address");
            input.nextLine();
            String deliveryAddressCheck = input.nextLine();

            if (deliveryAddressCheck.equalsIgnoreCase("Yes")) {
                deliveryAddress = this.getAddress();
                check = false;
            } else if (deliveryAddressCheck.equalsIgnoreCase("No")) {
                deliveryAddress = input.nextLine();
                check = false;
            } else {
                System.out.println("InValid! Input");
            }
        } while (check);

        return new Delivery(deliveryID, deliveryAddress, 100);
    }

    public Payment customerPayment(Scanner input, double totalPrice) {
        System.out.println("\n*********** Displaying Order Payment ***********");
        System.out.println("Choose Payment Method:");
        System.out.println("1. Cash On Delivery");
        System.out.println("2. Online Payment");

        int choice = 0;
        try {
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input! Number Only.");
            input.next();
            return customerPayment(input, totalPrice);  // Retry the payment selection in case of invalid input
        }

        int paymentId = (int) (Math.random() * 9000) + 1000;

        double amount = totalPrice;

        Date paymentDate = new Date();

        switch (choice) {
            case 1:
                return new CashOnDelivery(paymentId, amount, paymentDate);

            case 2:
                input.nextLine();  
                System.out.print("Enter Cardholder's Name: ");
                String cardholderName = input.nextLine();

                System.out.print("Enter Card Number: ");
                long cardNumber = input.nextLong();

                System.out.print("Enter Card Expiry Date (MM/YYYY): ");
                String expiry = input.next();
                Date expiryDate = new Date();  
                System.out.println("************************************" + expiryDate + expiry);
                return new OnlinePayment(cardNumber, expiryDate, cardholderName, paymentId, amount, paymentDate);

            default:
                System.out.println("Invalid choice! Defaulting to Cash On Delivery.");
                return new CashOnDelivery(paymentId, amount, paymentDate);
        }
    }
}