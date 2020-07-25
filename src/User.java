import java.util.HashMap;

public class User {
    private int userID;
    public static int staticID = 0;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userAddress;
    private int userZip;
    private String userPhone;

    public static HashMap<Integer, User> users = new HashMap<>();

    public User(String userFirstName, String userLastName, String userEmail, String userAddress, int userZip, String userPhone) {
        setUserFirstName(userFirstName);
        setUserLastName(userLastName);
        setUserAddress(userAddress);
        setUserEmail(userEmail);
        setUserZip(userZip);
        setUserPhone(userPhone);
        staticID++;
        setUserID(staticID);

        users.put(this.userID, this);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserZip() {
        return userZip;
    }

    public void setUserZip(int userZip) {
        this.userZip = userZip;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
