import java.util.HashMap;

public class Shop {
    private int shopID = 123;
    private String shopName;
    private String shopAddress;

    //Holds Product Id and the respective Product in this shop
    private HashMap<Integer, Product> shopProductHash = new HashMap<>();

    public static HashMap<Integer, Shop> shops = new HashMap<>();

    public Shop(String shopName, String shopAddress, HashMap<Integer, Product> shopProductHash) {

        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopProductHash = shopProductHash;
        shops.put(this.shopID, this);


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

    public HashMap<Integer, Product> getShopProductHash() {
        return shopProductHash;
    }

    public void setShopProductHash(HashMap<Integer, Product> shopProductHash) {
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


}
