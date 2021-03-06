import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Order {
    public static int staticID = 0;
    private int orderID;
    private User user;
    private Shop shop;

    //Product id and Quantity added of each product;
    private HashMap<Integer, Integer> productQuantityInOrder = new HashMap<>();

    public static HashMap<Integer, Order> orders = new HashMap<>();

    public Order(User user, Shop shop, HashMap<Integer, Integer> productQuantityInOrder) throws OutOfStockException {


        staticID++;
        setOrderID(staticID);
        this.user = user;
        this.shop = shop;

        //Decreases the quantity of each Product in the products hashmap.
        for (Map.Entry<Integer, Integer> entryProduct : productQuantityInOrder.entrySet()) {
            try {
                if (shop.getShopProductHash().containsKey(entryProduct.getKey())) {
                    //Throws exception if the order quantity value is higher than the product quantity available and does not add this to the final order
                    if ((shop.getShopProductHash().get(entryProduct.getKey()) - entryProduct.getValue()) < 0) {
                        productQuantityInOrder.replace(entryProduct.getKey(), shop.getShopProductHash().get(entryProduct.getKey()));
                        shop.getShopProductHash().replace(entryProduct.getKey(), 0);

                        this.productQuantityInOrder = productQuantityInOrder;

                        if (shop.getShopProductHash().get(entryProduct.getKey()) <= 5) {
                            //Records message in a file if product is going below stock;
                            reportLowStock(Product.products.get(entryProduct.getKey()).getProductID(), shop);
                        }

                        throw new OutOfStockException("Sorry, We only have " + shop.getShopProductHash().get(entryProduct.getKey()) + " in our warehouse. Your order has been adjusted based on the available stock.");

                    } else {
                        shop.getShopProductHash().replace(entryProduct.getKey(), shop.getShopProductHash().get(entryProduct.getKey()) - entryProduct.getValue());
                        this.productQuantityInOrder = productQuantityInOrder;

                        if (shop.getShopProductHash().get(entryProduct.getKey()) <= 5) {
                            //Records message in a file if product is going below stock;
                            reportLowStock(Product.products.get(entryProduct.getKey()).getProductID(), shop);
                        }
                    }


                }

            } catch (OutOfStockException e) {
                System.out.println(e);
            }


        }
        orders.put(this.orderID, this);

        printUserReport(user, this.orderID, shop, productQuantityInOrder);


    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public HashMap<Integer, Integer> getProductQuantityInOrder() {
        return productQuantityInOrder;
    }

    public void setProductQuantityInOrder(HashMap<Integer, Integer> productQuantityInOrder) {
        this.productQuantityInOrder = productQuantityInOrder;
    }

    public static void printUserReport(User user, int orderID, Shop shop, HashMap<Integer, Integer> productQuantityInOrder) {
        try {
            File file = new File("./src/TextFiles/" + user.getUserFirstName() + user.getUserLastName() + ".txt"); // Create file
            if (file.createNewFile()) { // Use createNewFile() method
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        try {
            FileWriter fileWrite = new FileWriter("./src/TextFiles/" + user.getUserFirstName() + user.getUserLastName() + ".txt", true);
            PrintWriter printWrite = new PrintWriter(fileWrite);

            printWrite.println(" ");

            printWrite.printf("%-16s %-20s %-20s %-32s %-16s %-16s %-16s %n", "OrderID", "UserName", "Shop", "Product", "Price", "Quantity", "Cost");
            double totalCost = 0;

            for (Map.Entry<Integer, Integer> entryProduct : productQuantityInOrder.entrySet()) {
                printWrite.printf("%-16d %-20s %-20s %-32s %-16.2f %-16d %-16.2f %n", orderID,
                        user.getUserFirstName() + user.getUserLastName(),
                        shop.getShopName(),
                        Product.products.get(entryProduct.getKey()).getProductName(),
                        Product.products.get(entryProduct.getKey()).getProductPrice(),
                        entryProduct.getValue(),
                        (entryProduct.getValue() * Product.products.get(entryProduct.getKey()).getProductPrice()));
                totalCost += (entryProduct.getValue() * Product.products.get(entryProduct.getKey()).getProductPrice());
            }

            printWrite.println("Total Cost = " + totalCost);

            printWrite.close();
            fileWrite.close();
            System.out.println("Successfully wrote to user file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void reportLowStock(int productID, Shop shop) {
        try {
            File file = new File("./src/TextFiles/LowStock.txt"); // Create file
            if (file.createNewFile()) { // Use createNewFile() method
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        try {
            FileWriter fileWrite = new FileWriter("./src/TextFiles/LowStock.txt", true);
            PrintWriter printWrite = new PrintWriter(fileWrite);

            printWrite.println(" ");

            printWrite.printf("%-16s %-32s %-90s %-16s %-16s %n", "ProductID", "Name", "Description", "Price", "Quantity");

            printWrite.printf("%-16d %-32s %-90s %-16.2f %-16d %n",
                    Product.products.get(productID).getProductID(),
                    Product.products.get(productID).getProductName(),
                    Product.products.get(productID).getProductDescription(),
                    Product.products.get(productID).getProductPrice(),
                    shop.getShopProductHash().get(productID)
            );

            printWrite.close();
            fileWrite.close();
            System.out.println("Successfully wrote to Low Stock file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printAllOrders() {
        for (Map.Entry<Integer, Order> entryOrder : orders.entrySet()) {
            System.out.println("OrderNumber: " + entryOrder.getKey());
            System.out.println("User Name: " + entryOrder.getValue().getUser().getUserFirstName() + " " + entryOrder.getValue().getUser().getUserLastName());
            System.out.println("Shop Name: " + entryOrder.getValue().getShop().getShopName());
            System.out.printf("%-16s %-32s %-16s %-16s %-16s %n", "ProductID", "Name", "Price", "Quantity", "Cost");
            double totalCost = 0;
            for (Map.Entry<Integer, Integer> entryOrderProduct : entryOrder.getValue().getProductQuantityInOrder().entrySet()) {
                System.out.printf("%-16d %-32s %-16.2f %-16d %-16.2f %n",
                        Product.products.get(entryOrderProduct.getKey()).getProductID(),
                        Product.products.get(entryOrderProduct.getKey()).getProductName(),
                        Product.products.get(entryOrderProduct.getKey()).getProductPrice(),
                        entryOrderProduct.getValue(),
                        (entryOrderProduct.getValue() * Product.products.get(entryOrderProduct.getKey()).getProductPrice()));
                totalCost += (entryOrderProduct.getValue() * Product.products.get(entryOrderProduct.getKey()).getProductPrice());

            }
            System.out.println("Total Cost of this order is " + totalCost + "\n");
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", user=" + user +
                ", shop=" + shop +
                ", productQuantityInOrder=" + productQuantityInOrder +
                '}';
    }
}
