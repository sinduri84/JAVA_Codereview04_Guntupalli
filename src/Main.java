import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /*(10) Design a simple menu - In Main Class;
    (5) Create a class Product: A product can only have a max. stock of 15 items. - Created StockLimit15Exception to make sure the quantity of product never crosses 15; This exception is used in the constructor of the Product Class;
    (5) Create  a method to add products to the shop - Created in Product Class;
    (5) Create a class Shop - Created and all the products in the products Hashmap is automatically added to the shop as a variable Hashmap;
    (5) Create a class User - Created;
    (25) A user should be able to buy products from the shop - Order Class created to track the orders by each user; Order class takes user, shop and a hashmap containing product id and the respective quantity; Test Data shown below. The products hashmap is printed to show the decrease in quantity after each order; This functionality is in the Order Constructor;
    (20) Display the purchase process, with a few examples, in your main() to prove everything works accordingly - 6 orders created and shown in console;
    (10) if the stock of a product gets below 5 it should inform the staff about that. - When a product is added or order is created, if the final quantity of order is less than 5, the message is sent to a "LowStock" text file. The method reportLowStock is created in Order Class to achieve this.
If one of the staff members tries to add a product to the shop but the stock is already full it should throw a custom exception - StockLimitReachedException. - Created a custom exception and it is used in the Product class
    (15) Extend your menu to show all users registered to the shop in a nicely formatted way. - Achieved in Option 7 and by creating a method printAllUsers in User Class.
    How are the Shop and User class connected? - They are connected by the Order Class;
    (20) Create a method printReport(), which creates an external file “report.txt” - Created a method printUserReport to create a user specific file, which dynamically updates a user order data as soon as the order is placed.


    *
    * */

    public static void main(String[] args) throws OutOfStockException, StockLimit15Exception {

        Shop shop1 = new Shop("Name1", "Address1", Product.products);

        System.out.println(shop1.toString());

        Product.addProduct("Shoe1", "You wear them to protect your feet!", 95.28, Product.ProductCategory.Apparel, 14, shop1);

        Product.addProduct("Electronic1", "Work or Play Time!", 258.598, Product.ProductCategory.Electronics, 14, shop1);

        Product.addProduct("Watch1", "Hand Prettifier!", 54.26, Product.ProductCategory.Watches, 16, shop1);

        Product.addProduct("Shoe2", "You wear them to protect your feet", 98.56, Product.ProductCategory.Apparel, 13, shop1);

        Product.addProduct("Cleanser", "Clean your face or Hands!", 98.56, Product.ProductCategory.Miscellaneous, 6, shop1);

        Product.addProduct("Shoe3", "You wear them to protect your feet", 198.56, Product.ProductCategory.Apparel, 15, shop1);

        Product.addProduct("Electronic2", "Work or Play Time!", 1258.598, Product.ProductCategory.Electronics, 13, shop1);

        Product.addProduct("Watch2", "Hand Prettifier!", 154.26, Product.ProductCategory.Watches, 12, shop1);

        Product.addProduct("Shoe4", "You wear them to protect your feet", 198.56, Product.ProductCategory.Apparel, 19, shop1);

        Product.addProduct("Cat Bed", "Treat your cats right!", 298.56, Product.ProductCategory.Miscellaneous, 4, shop1);




        User user1 = new User("Black", "Widow", "bwidow@gmail.com", "Karlsplatz", 85281, "6641826474");
        User user2 = new User("Bruce", "Banner", "hulk@gmail.com", "Orange St", 25401, "6641826475");
        User user3 = new User("Captain", "America", "cap@gmail.com", "Somewhere", 1040, "6641826476");
        User user4 = new User("Thor", "Odinson", "thor@gmail.com", "Asgard", 220010, "6641826477");

        System.out.println("\nOriginal Product Stock");
        Product.printProducts();


        //Order1
        System.out.println("\nOrder1");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser1 = new HashMap<>();
        productsUser1.put(1, 12);
        productsUser1.put(2, 14);
        Order order1 = new Order(User.users.get(1), Shop.shops.get(123), productsUser1);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order2
        System.out.println("\nOrder2");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser2 = new HashMap<>();
        productsUser2.put(3, 12);
        productsUser2.put(4, 7);
        Order order2 = new Order(User.users.get(2), Shop.shops.get(123), productsUser2);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order3
        System.out.println("\nOrder3");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser3 = new HashMap<>();
        productsUser3.put(7, 13);
        productsUser3.put(8, 6);
        Order order3 = new Order(User.users.get(3), Shop.shops.get(123), productsUser3);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order4
        System.out.println("\nOrder4");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser4 = new HashMap<>();
        productsUser4.put(5, 1);
        productsUser4.put(6, 1);
        Order order4 = new Order(User.users.get(3), Shop.shops.get(123), productsUser4);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order5
        System.out.println("\nOrder5");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser5 = new HashMap<>();
        productsUser5.put(5, 1);
        productsUser5.put(6, 1);
        Order order5 = new Order(User.users.get(4), Shop.shops.get(123), productsUser5);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order6
        System.out.println("\nOrder6");
        //Hashmap takes Product ID and ProductQuantity
        HashMap<Integer, Integer> productsUser6 = new HashMap<>();
        productsUser6.put(5, 1);
        productsUser6.put(6, 1);
        Order order6 = new Order(User.users.get(4), Shop.shops.get(123), productsUser6);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Menu Design starts here;
        Scanner inputText = new Scanner(System.in);
        Scanner inputNumber = new Scanner(System.in);
        Scanner inputDouble = new Scanner(System.in);

        System.out.println(order1.toString());

        try {

            boolean whileboolean1 = true;
            System.out.println("\n\nWelcome Staff, let's make this store better!");

            int userId = 0;

            while (whileboolean1) {

                System.out.print("\nPress 1 to display all products in this store" +
                        "\nPress 2 if you want to see products by Category" +
                        "\nPress 3 to see all products with stock less than 5" +
                        "\nPress 4 to see all products out of stock" +
                        "\nPress 5 to add a new product" +
                        "\nPress 6 to see all orders" +
                        "\nPress 7 to see all users registered" +
                        "\nPress 8 to exit" +
                        "\nOption: ");
                int inputOption1 = inputNumber.nextInt();

                switch ((inputOption1)) {
                    case (1):
                        Product.printProducts();
                        break;
                    case (2):
                        int counter1 = 0;
                        for (Product.ProductCategory category : Product.ProductCategory.values()) {
                            counter1++;
                            System.out.println("Press " + counter1 + " for the category, " + category);
                        }
                        int inputOption2 = inputNumber.nextInt();

                        System.out.printf("%n%-10s %-20s %-50s %-16s %-16s %-32s %n", "ProductID", "Name", "Description", "Price", "Quantity", "Category");
                        for (Map.Entry<Integer, Product> entryProduct : Product.products.entrySet()) {

                            if (entryProduct.getValue().getProductCategory().equals(Array.get(Product.ProductCategory.values(), (inputOption2 - 1)))) {
                                System.out.printf("%-10s %-20s %-50s %-16.2f %-16d %-32s %n", entryProduct.getKey(),
                                        entryProduct.getValue().getProductName(),
                                        entryProduct.getValue().getProductDescription(),
                                        entryProduct.getValue().getProductPrice(), entryProduct.getValue().getProductQuantity(),
                                        entryProduct.getValue().getProductCategory());
                            }
                        }
                        break;
                    case (3):
                        System.out.printf("%n%-10s %-20s %-50s %-16s %-16s %-32s %n", "ProductID", "Name", "Description", "Price", "Quantity", "Category");
                        for (Map.Entry<Integer, Product> entryProduct : Product.products.entrySet()) {

                            if (entryProduct.getValue().getProductQuantity() < 5) {
                                System.out.printf("%-10s %-20s %-50s %-16.2f %-16d %-32s %n", entryProduct.getKey(),
                                        entryProduct.getValue().getProductName(),
                                        entryProduct.getValue().getProductDescription(),
                                        entryProduct.getValue().getProductPrice(), entryProduct.getValue().getProductQuantity(),
                                        entryProduct.getValue().getProductCategory());
                            }
                        }
                        break;
                    case (4):
                        System.out.printf("%n%-10s %-20s %-50s %-16s %-16s %-32s %n", "ProductID", "Name", "Description", "Price", "Quantity", "Category");
                        for (Map.Entry<Integer, Product> entryProduct : Product.products.entrySet()) {

                            if (entryProduct.getValue().getProductQuantity() == 0) {
                                System.out.printf("%-10s %-20s %-50s %-16.2f %-16d %-32s %n", entryProduct.getKey(),
                                        entryProduct.getValue().getProductName(),
                                        entryProduct.getValue().getProductDescription(),
                                        entryProduct.getValue().getProductPrice(), entryProduct.getValue().getProductQuantity(),
                                        entryProduct.getValue().getProductCategory());
                            }

                        }
                        break;
                    case (5):
                        System.out.print("\nEnter the name of the product: ");
                        String inputProductName = inputText.nextLine();
                        System.out.print("\nEnter the description of the product: ");
                        String inputProductDescription = inputText.nextLine();
                        System.out.print("\nEnter the price of the product: ");
                        double inputProductPrice = inputDouble.nextDouble();

                        int counter2 = 0;
                        for (Product.ProductCategory category : Product.ProductCategory.values()) {
                            counter2++;
                            System.out.println("Press " + counter2 + " to choose the category, " + category);
                        }
                        int inputOption3 = inputNumber.nextInt();

                        String inputProductCategory = String.valueOf(Array.get(Product.ProductCategory.values(), (inputOption3-1)));

                        System.out.print("\nEnter the quantity of the product: ");
                        int inputProductQuantity = inputNumber.nextInt();

                        Product.addProduct(inputProductName, inputProductDescription, inputProductPrice, (Product.ProductCategory) Array.get(Product.ProductCategory.values(), (inputOption3-1)), inputProductQuantity, shop1);

                        Product.printProducts();
                        break;
                    case(6):
                        Order.printAllOrders();
                        break;
                    case (7):
                        User.printAllUsers();
                        break;
                    case (8):
                        whileboolean1 = false;
                        break;
                    default:
                        System.out.println("Please select one of the options available!");
                        break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Please press the right formatted input!");
        }

        inputNumber.close();
        inputText.close();



    }




}
