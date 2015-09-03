import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Store {
  private String name;
  private String address;
  private String phone_number;
  private int id;


  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public Store (String name, String address, String phoneNumber) {
    this.name = name;
    this.address = address;
    this.phone_number = phoneNumber;
  }

  public static List<Store> all() {
    String sql = "SELECT * FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }


}
