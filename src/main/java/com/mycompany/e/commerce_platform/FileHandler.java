package com.mycompany.e.commerce_platform;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
//*************************************************** Store Owner ******************************************************************
    // Writing store owner data to text file
    public void writeStoreOwners(StoreOwner storeOwner) throws IOException {
        File file = new File("StoreOwnerFile.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("New Store Owner File Created..... " + file.getName());
            System.out.println("*********************************************************");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            String storeOwnerData = storeOwner.getStoreOwnerID() + "," + storeOwner.getName() + "," + storeOwner.getEmail() + "," +
                    storeOwner.getPhoneNumber() + "," + storeOwner.getAddress() + "\n";
            writer.write(storeOwnerData);
        } catch (IOException e) {
            System.out.println("Some Error Occurred While Writing..... ");
            System.out.println("**************************************");
        }
    }

    // Reading store owner data from text file
    public ArrayList<StoreOwner> getStoreOwners() {
        ArrayList<StoreOwner> storeOwners = new ArrayList<>();
        File file = new File("StoreOwnerFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                long id = Long.parseLong(data[0]);
                String name = data[1];
                String email = data[2];
                long phoneNumber = Long.parseLong(data[3]);
                String address = data[4];

                StoreOwner storeOwner = new StoreOwner(name, email, phoneNumber, address, id);
                storeOwners.add(storeOwner);
            }
        } catch (IOException e) {
//            System.out.println("Error reading the file: " + e.getMessage());
        }
        return storeOwners;
    }

    // Finding store owner by ID
    public StoreOwner getStoreOwnerID(long id) {
        ArrayList<StoreOwner> storeOwnerList = getStoreOwners();
        if (storeOwnerList != null) {
            for (StoreOwner storeOwner : storeOwnerList) {
                if (id == storeOwner.getStoreOwnerID()) {
                    return storeOwner;
                }
            }
        }
//        System.out.println("Store Owner Not Found........");
        return null;
    }
//*************************************************** Store Owner ******************************************************************

//*************************************************** Store ******************************************************************

    // Writing store data to text file
    public void writeStores(Store store) throws IOException {
        File file = new File("StoresFile.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Store File Created...." + file.getName());
            System.out.println("********************************************");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            String storeData = store.getStoreId() + "," + store.getStoreName() + "," + store.getLocation() + "," +
                    store.getStoreOwner().getStoreOwnerID() + "\n";
            writer.write(storeData);
        } catch (IOException e) {
            System.out.println("Some Error Occurred While Writing..... ");
            System.out.println("**************************************");
        }
    }

    // Reading store data from text file
    public ArrayList<Store> readStores() {
        ArrayList<Store> stores = new ArrayList<>();
        File file = new File("StoresFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                long storeId = Long.parseLong(data[0]);
                String storeName = data[1];
                String location = data[2];
                long storeOwnerId = Long.parseLong(data[3]);

                StoreOwner storeOwner = getStoreOwnerID(storeOwnerId);
                if (storeOwner != null) {
                    Store store = new Store(storeId, storeOwner, storeName, location);
                    stores.add(store);
                }
            }
        } catch (IOException e) {
//            System.out.println("Error reading the file: " + e.getMessage());
        }
        return stores;
    }

    // Finding store by ID
    public Store getStoreID(long storeID) {
        ArrayList<Store> stores = readStores();
        if (stores != null) {
            for (Store store : stores) {
                if (storeID == store.getStoreId()) {
                    return store;
                }
            }
        }
        System.out.println("Store Not Found........");
        return null;
    }

    // Store Owner specific stores
    public ArrayList<Store> getStores(long storeOwnerID) {
        ArrayList<Store> stores = readStores();
        ArrayList<Store> storeOwnerStores = new ArrayList<>();
        if (stores != null) {
            for (Store store : stores) {
                if (store.getStoreOwner ().getStoreOwnerID() == storeOwnerID) {
                    storeOwnerStores.add(store);
                }
            }
            return storeOwnerStores;
        }
        return storeOwnerStores;
    }

    // Delete a store by ID
    public void deleteStore(long storeID) throws IOException {
        File file = new File("StoresFile.txt");
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.out.println("********************************************");
            return;
        }

        ArrayList<Store> stores = readStores();
        if (stores == null || stores.isEmpty()) {
            System.out.println("No stores available to delete.");
            return;
        }

        boolean storeRemoved = stores.removeIf(store -> store.getStoreId() == storeID);

        try (FileWriter writer = new FileWriter(file)) {
            for (Store store : stores) {
                String storeData = String.format("%d,%s,%s,%d%n",
                        store.getStoreId(), store.getStoreName(), store.getLocation(),
                        store.getStoreOwner().getStoreOwnerID());
                writer.write(storeData);
            }
            if (storeRemoved) {
                System.out.println("Store deleted successfully.");
            } else {
                System.out.println("Store with ID " + storeID + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
//*************************************************** Store ******************************************************************

    // Writing product data to text file
    public void writeProducts(Product product) throws IOException {
        File file = new File("ProductsFile.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Product File Created...." + file.getName());
            System.out.println("********************************************");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            String productData = product.getProductID() + "," + product.getProductName() + "," + product.getPrice() + "," +
                    product.getStockQuantity() + "," + product.getStore().getStoreId() + "\n";
            writer.write(productData);
        } catch (IOException e) {
            System.out.println("Some Error Occurred While Writing..... ");
            System.out.println("**************************************");
        }
    }

    // Reading product data from text file
    public ArrayList<Product> readProducts() {
        ArrayList<Product> products = new ArrayList<>();
        File file = new File("ProductsFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                long productID = Long.parseLong(data[0]);
                String productName = data[1];
                double productPrice = Double.parseDouble(data[2]);
                int productStock = Integer.parseInt(data[3]);
                long productStoreId = Long.parseLong(data[4]);

                Store store = getStoreID(productStoreId);
                if (store != null) {
                    Product product = new Product(productID, productName, productPrice, productStock, store);
                    products.add(product);
                }
            }
        } catch (IOException e) {
//            System.out.println("Error reading the file: " + e.getMessage());
        }
        return products;
    }

    // Finding Product by ID
    public Product getProductID(long productID) {
        ArrayList<Product> products = readProducts();
        if (products != null) {
            for (Product product : products) {
                if (productID == product.getProductID()) {
                    return product;
                }
            }
        }
        System.out.println("Product Not Found........");
        return null;
    }

    // Store specific products
    public ArrayList<Product> getProducts(long storeID) {
        ArrayList<Product> products = readProducts();
        ArrayList<Product> storeProducts = new ArrayList<>();
        if (products != null) {
            for (Product product : products) {
                if (product.getStore().getStoreId() == storeID) {
                    storeProducts.add(product);
                }
            }
            return storeProducts;
        }
        return storeProducts;
    }

    // Delete a product by ID
    public void deleteProduct(long productID) throws IOException {
        File file = new File("ProductsFile.txt");
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.out.println("********************************************");
            return;
        }

        ArrayList<Product> products = readProducts();
        if (products == null || products.isEmpty()) {
            System.out.println("No products available to delete.");
            return;
        }

        boolean productRemoved = products.removeIf(product -> product.getProductID() == productID);

        try (FileWriter writer = new FileWriter(file)) {
            for (Product product : products) {
                String productData = String.format("%d,%s,%.2f,%d,%d%n",
                        product.getProductID(), product.getProductName(), product.getPrice(),
                        product.getStockQuantity(), product.getStore().getStoreId());
                writer.write(productData );
            }
            if (productRemoved) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product with ID " + productID + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
 public void writeCustomers(Customer customer) throws IOException {
        File file = new File("customers.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Customers File Created: " + file.getName());
            System.out.println("********************************************");
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            String customerData = customer.getCustomerID() + "," + customer.getName() + "," + customer.getEmail() + "," +
                    customer.getAddress() + "," + customer.getPhoneNumber() + "\n";
            writer.write(customerData);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read all customers from the file
    public ArrayList<Customer> readCustomers() {
        File file = new File("customers.txt");
        ArrayList<Customer> customers = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("Customers File does not exist.");
            return customers;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                long id = Long.parseLong(data[0]);
                String name = data[1];
                String email = data[2];
                String address = data[3];
                long phoneNumber = Long.parseLong(data[4]);
                Customer customer = new Customer(id, name, email, address, phoneNumber);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return customers;
    }

    // Finding a customer by ID
    public Customer getCustomerById(long customerId) {
        ArrayList<Customer> customers = readCustomers();
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerId) {
                return customer;
            }
        }
        return null;
    }
    // Read all customers from the file
//    public ArrayList<Customer> readCustomers() {
//        File file = new File("customers.txt");
//        ArrayList<Customer> customers = new ArrayList<>();
//
//        if (!file.exists()) {
//            System.out.println("customer File does not exist.");
//            return customers;
//        }
//
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                long id = Long.parseLong(data[0]);
//                String name = data[1];
//                String email = data[2];
//                String address = data[3];
//                Long phoneNumber = data.length > 4 ? Long.parseLong(data[4]) : null;
//                Customer customer = new Customer(id, name, email, address, phoneNumber);
//                customers.add(customer);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//        return customers;
//    }

//    public void writeCustomers(Customer customer) throws IOException {
//        File file = new File("customers.txt");
//           if (!file.exists()) {
//               file.createNewFile();
//               System.out.println("New Customer File Created..... " + file.getName());
//               System.out.println("*********************************************************");
//           }
//       try {
//           FileWriter writer = new FileWriter("customers.txt", true);
//           writer.write(customer.toString() + "\n");
//           writer.close();
//       } catch (IOException e) {
//           System.out.println("Error writing to file: " + e.getMessage());
//       }
//    }

    // Add a customer to the file
    public void addCustomer(Customer customer) throws IOException {
    ArrayList<Customer> customers = readCustomers();
    if (customers == null) {
        customers = new ArrayList<>();
    }
    customers.add(customer);
    writeCustomers(customer);
}

// Delete a customer by ID from the file
public void deleteCustomer(long customerID, Customer newcustomer) throws IOException {
    ArrayList<Customer> customers = readCustomers();
    if (customers != null) {
        boolean removed = customers.removeIf(customer -> customer.getCustomerID() == customerID);

        if (removed) {
            writeCustomers(newcustomer);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer with ID " + customerID + " not found.");
        }
    } else {
        System.out.println("No customers found.");
    }
    }
// Reading order data from text file
    // Reading all orders from the file
    public ArrayList<Order> readOrders() {
        File file = new File("OrdersFile.txt");
        ArrayList<Order> orders = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return orders;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Reading order details
                long orderId = Long.parseLong(data[0]);
                long customerId = Long.parseLong(data[1]);
                String[] productIDs = data[2].split("-");
                double totalCost = Double.parseDouble(data[3]);
                String orderDate = data[4];

            
                Customer customer = getCustomerById(customerId);//

                // getting products by their IDs
                ArrayList<Product> products = new ArrayList<>();
                for (String productID : productIDs) {
                    Product product = getProductID(Long.parseLong(productID)); // 
                    if (product != null) {
                        products.add(product);
                    }
                }

                // Create the Order object
                Order order = new Order(orderId, customer, products,orderDate);
                order.setTotalCost(totalCost);
                orders.add(order);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return orders;
    }


// Write a single order to text file
    public void writeOrder(Order order) throws IOException {
        File file = new File("OrdersFile.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Orders File Created: " + file.getName());
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            String orderData = order.getOrderId() + "," + order.getCustomer().getCustomerID() + "," + order.getProducts() + "," +
                    order.getTotalCost() + "," + order.getOrderDate() + "\n";
            writer.write(orderData);
        } catch (IOException e) {
            System.out.println("Some Error Occurred While Writing Order..... ");
        }
    }

// Write all orders to the text file (overwrite)
    public void writeOrders(ArrayList<Order> orders) throws IOException {
        File file = new File("OrdersFile.txt");

        try (FileWriter writer = new FileWriter(file)) {
            for (Order order : orders) {
                String orderData = order.getOrderId() + "," + order.getCustomer().getCustomerID() + "," + order.getProducts() + "," +
                        order.getTotalCost() + "," + order.getOrderDate() + "\n";
                writer.write(orderData);
            }
        } catch (IOException e) {
            System.out.println("Some Error Occurred While Writing All Orders..... ");
        }
    }

// Add a new order
    public void addOrder(Order order) throws IOException {
        ArrayList<Order> orders = readOrders();
        orders.add(order);
        writeOrders(orders);
    }

// Delete an order by ID
    public void deleteOrder(long orderId) throws IOException {
        ArrayList<Order> orders = readOrders();
        boolean removed = orders.removeIf(order -> order.getOrderId() == orderId);

        if (removed) {
            writeOrders(orders);
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
    }   

public void manageStoreOrder(long storeId) throws FileNotFoundException, IOException {

    File file = new File("OrdersFile.txt");

    if (!file.exists()) {
        System.out.println("File does not exist.");
        return;
    } else {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Reading order details
                long orderId = Long.parseLong(data[0]);
                long customerId = Long.parseLong(data[1]);
                String[] productIDs = data[2].replaceAll("[\\[\\]com.mycompany.e.commerce_platform.Product@]", "").split("-");
                double totalCost = Double.parseDouble(data[3]);
                String orderDate = data[4];

                Customer customer = getCustomerById(customerId);
                if (customer == null) {
                    System.out.println("Customer with ID " + customerId + " not found.");
                    continue;  // Skip to the next iteration if the customer doesn't exist
                }

                // getting products by their IDs
                ArrayList<Product> products = new ArrayList<>();
                for (String productID : productIDs) {
                    Product product = getProductID(Long.parseLong(productID.trim())); // Trim to remove extra spaces
                    if (product != null && product.getStore().getStoreId() == storeId) {
                        products.add(product);
                    }
                }

                if (!products.isEmpty()) {
                    Order order = new Order(orderId, customer, products, orderDate);
                    order.setTotalCost(totalCost);
                    // Optionally print order details
                    System.out.println(order); // Adjust as needed for your Order class
                }
            }
        } catch (Exception e) {
            System.out.println("Some Error Occurred ..... ");
        }
    }
}

}