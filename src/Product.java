import java.util.HashMap;
import java.util.Map;

public class Product {
    private int productID;
    public static int staticID = 0;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;

    public enum ProductCategory{
        Electronics, Watches, Apparel, Movies, Miscellaneous;
    }

    private ProductCategory productCategory;

    public static HashMap<Integer, Product> products = new HashMap<>();

    //Throws Exception if Stock is more than 15 items per product
    public Product(String productName, String productDescription, double productPrice, ProductCategory productCategory, int productQuantity) throws StockLimit15Exception {
        try{
            if(productQuantity > 15) {
                throw new StockLimit15Exception(productName + " product has only 15 spaces.");
            } else {
                staticID++;
                setProductID(staticID);
                this.productName = productName;
                this.productDescription = productDescription;
                this.productPrice = productPrice;
                this.productCategory = productCategory;
                this.productQuantity =  productQuantity;
                products.put(this.getProductID(), this);
                System.out.println("New product created!");

            }

        } catch (StockLimit15Exception e) {
            System.out.println(e);
        }




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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public static void printProducts() {
        System.out.printf("%n%-10s %-20s %-50s %-16s %-16s %-32s %n", "ProductID", "Name", "Description", "Price", "Quantity", "Category");
        for(Map.Entry<Integer, Product> entryProduct : products.entrySet()) {
            System.out.printf("%-10s %-20s %-50s %-16.2f %-16d %-32s %n", entryProduct.getKey(), entryProduct.getValue().getProductName(),
                    entryProduct.getValue().getProductDescription(), entryProduct.getValue().getProductPrice(), entryProduct.getValue().getProductQuantity(),
                    entryProduct.getValue().getProductCategory());
        }
    }

    public static void addProduct(String productName, String productDescription, double productPrice, Product.ProductCategory productCategory, int productQuantity, Shop shop) throws StockLimit15Exception {
        Product product =  new Product(productName, productDescription, productPrice, productCategory, productQuantity);
        shop.setShopProductHash(Product.products);
        if(productQuantity < 5) {
            Order.reportLowStock(product.getProductID());
        }

    }
}
