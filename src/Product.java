import java.util.HashMap;
import java.util.Map;

public class Product {
    private int productID;
    public static int staticID = 0;
    private String productName;
    private String productDescription;
    private double productPrice;

    public enum ProductCategory{
        Electronics, Watches, Apparel, Movies, Miscellaneous;
    }

    private ProductCategory productCategory;

    public static HashMap<Integer, Product> products = new HashMap<>();

    //Throws Exception if Stock is more than 15 items per product
    public Product(String productName, String productDescription, double productPrice, ProductCategory productCategory) {
                staticID++;
                setProductID(staticID);
                this.productName = productName;
                this.productDescription = productDescription;
                this.productPrice = productPrice;
                this.productCategory = productCategory;
                products.put(this.getProductID(), this);
                System.out.println("New product created!");
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public static void printProducts(Shop shop) {
        System.out.printf("%n%-10s %-20s %-50s %-16s %-32s %-16s %n", "ProductID", "Name", "Description", "Price", "Category", "Quantity");
        for(Map.Entry<Integer, Product> entryProduct : products.entrySet()) {
            System.out.printf("%-10s %-20s %-50s %-16.2f %-32s %-16d %n", entryProduct.getKey(), entryProduct.getValue().getProductName(),
                    entryProduct.getValue().getProductDescription(), entryProduct.getValue().getProductPrice(),
                    entryProduct.getValue().getProductCategory(),
                    shop.getShopProductHash().get(entryProduct.getKey()));
        }
    }


}
