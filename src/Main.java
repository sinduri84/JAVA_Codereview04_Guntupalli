import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws OutOfStockException, StockLimit15Exception {

        Product product1 = new Product("Shoe1", "You wear them to protect your feet", 98.56, Product.ProductCategory.Apparel, 15);
        Product product2 = new Product("Electronic1", "Work or Play Time!", 258.598, Product.ProductCategory.Electronics, 14);
        Product product3 = new Product("Watch1", "Hand Prettifier!", 54.26, Product.ProductCategory.Watches, 16);
        Product product4 = new Product("Shoe2", "You wear them to protect your feet", 98.56, Product.ProductCategory.Apparel, 13);
        Product product5 = new Product("Cleanser", "Clean your face or Hands!", 98.56, Product.ProductCategory.Miscellaneous, 6);
        Product product6 = new Product("Shoe3", "You wear them to protect your feet", 198.56, Product.ProductCategory.Apparel, 15);
        Product product7 = new Product("Electronic2", "Work or Play Time!", 1258.598, Product.ProductCategory.Electronics, 13);
        Product product8 = new Product("Watch2", "Hand Prettifier!", 154.26, Product.ProductCategory.Watches, 12);
        Product product9 = new Product("Shoe4", "You wear them to protect your feet", 198.56, Product.ProductCategory.Apparel, 19);
        Product product10 = new Product("Cat Bed", "Treat your cats right!", 298.56, Product.ProductCategory.Miscellaneous, 4);


        Shop shop1 = new Shop("Name1", "Address1", Product.products);

        System.out.println(shop1.toString());

        User user1 = new User("Black", "Widow", "bwidow@gmail.com", "Karlsplatz", 85281, "6641826474");
        User user2 = new User("Bruce", "Banner", "hulk@gmail.com", "Orange St", 25401, "6641826475");
        User user3 = new User("Captain", "America", "cap@gmail.com", "Somewhere", 1040, "6641826476");
        User user4 = new User("Thor", "Odinson", "thor@gmail.com", "Asgard", 220010, "6641826477");

        System.out.println("\nOriginal Product Stock");
        Product.printProducts();

        //Order1
        System.out.println("\nOrder1");
        HashMap<Integer, Integer> productsUser1 = new HashMap<>();
        productsUser1.put(1, 12);
        productsUser1.put(2, 14);
        Order order1 = new Order(User.users.get(1), Shop.shops.get(123), productsUser1);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order2
        System.out.println("\nOrder2");
        HashMap<Integer, Integer> productsUser2 = new HashMap<>();
        productsUser2.put(3, 12);
        productsUser2.put(4, 7);
        Order order2 = new Order(User.users.get(2), Shop.shops.get(123), productsUser2);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order3
        System.out.println("\nOrder3");
        HashMap<Integer, Integer> productsUser3 = new HashMap<>();
        productsUser3.put(7, 13);
        productsUser3.put(8, 6);
        Order order3 = new Order(User.users.get(3), Shop.shops.get(123), productsUser3);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        //Order4
        System.out.println("\nOrder4");
        HashMap<Integer, Integer> productsUser4 = new HashMap<>();
        productsUser4.put(5, 1);
        productsUser4.put(6, 1);
        Order order4 = new Order(User.users.get(3), Shop.shops.get(123), productsUser4);

        System.out.println("\nProduct Stock after order");
        Product.printProducts();

        Scanner inputText = new Scanner(System.in);
        Scanner inputNumber = new Scanner(System.in);

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
                        "\nPress 6 to exit" +
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
                        break;
                    case (6):
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

    }
}
