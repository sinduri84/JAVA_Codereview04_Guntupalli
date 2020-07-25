import java.util.HashMap;
import java.util.Map;

public class Shop {
    private int shopID = 123;
    private String shopName;
    private String shopAddress;

    //Holds Product Id and the respective Product Quantity in this shop
    private HashMap<Integer, Integer> shopProductHash = new HashMap<>();

    public static HashMap<Integer, Shop> shops = new HashMap<>();

    public Shop(String shopName, String shopAddress, HashMap<Integer, Integer> shopProductHash) throws StockLimit15Exception {

        HashMap<Integer, Integer> newShopProductHash = new HashMap<>();
        for(Map.Entry<Integer, Integer> entryProduct : shopProductHash.entrySet()) {
            try{
                if(entryProduct.getValue() > 15) {
                    throw new StockLimit15Exception(Product.products.get(entryProduct.getKey()).getProductName() + " product can only take 15 spaces.");
                } else {
                    newShopProductHash.put(entryProduct.getKey(), entryProduct.getValue());
                }

            } catch (StockLimit15Exception e) {
                System.out.println(e);
            }
        }

        if(newShopProductHash.size() != 0) {
            this.shopName = shopName;
            this.shopAddress = shopAddress;
            this.shopProductHash = newShopProductHash;
            shops.put(this.shopID, this);
        }




    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public HashMap<Integer, Integer> getShopProductHash() {
        return shopProductHash;
    }

    public void setShopProductHash(HashMap<Integer, Integer> shopProductHash) {
        this.shopProductHash = shopProductHash;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopID=" + shopID +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopProductHash=" + shopProductHash +
                '}';
    }



    public static void addProduct(String productName, String productDescription, double productPrice, Product.ProductCategory productCategory, int productQuantity, Shop shop) throws StockLimit15Exception {
        try  {
            if(productQuantity > 15) {
                throw new StockLimit15Exception("Product can't have more than 15 items");
            } else  {
                Product product =  new Product(productName, productDescription, productPrice, productCategory);

                shop.getShopProductHash().put(product.getProductID(), productQuantity);
                if(productQuantity < 5) {
                    Order.reportLowStock(product.getProductID(), shop);
                }
            }
        } catch(StockLimit15Exception e) {
            System.out.println(e);
        }



    }


}
