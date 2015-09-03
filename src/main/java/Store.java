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

  @Override
  public boolean equals(Object otherStore) {
    if(!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName()) &&
             this.getAddress().equals(newStore.getAddress()) &&
             this.getPhoneNumber().equals(newStore.getPhoneNumber()) &&
             this.getId() == newStore.getId();
    }
  }

  public static List<Store> all() {
    String sql = "SELECT * FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores (name, address, phone_number) VALUES (:name, :address, :phone_number)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("address", this.address)
      .addParameter("phone_number", this.phone_number)
      .executeUpdate()
      .getKey();
    }
  }

  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stores WHERE id=:id";
      Store store = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
      return store;
      }
    }

  public void update(String name, String address, String phone_number) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET name = :name, address = :address, phone_number = :phone_number WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("phone_number", phone_number)
        .addParameter("id", id)
        .executeUpdate();
        }
      }

  public void addBrand(Brand brand) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores_brands (store_id, brand_id) VALUES (:store_id, :brand_id)";
      con.createQuery(sql)
        .addParameter("store_id", this.getId())
        .addParameter("brand_id", brand.getId())
        .executeUpdate();
      }
    }

  public ArrayList<Brand> getBrands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT brand_id FROM stores_brands WHERE store_id =:store_id";
      List<Integer> brandIds = con.createQuery(sql)
        .addParameter("store_id", this.getId())
        .executeAndFetch(Integer.class);

        ArrayList<Brand> brands = new ArrayList<Brand>();

      for(Integer brandId : brandIds) {
        String brandQuery = "SELECT * FROM brands WHERE id=:brandId";
        Brand brand = con.createQuery(brandQuery)
          .addParameter("brandId", brandId)
          .executeAndFetchFirst(Brand.class);
        brands.add(brand);
      }return brands;
    }
  }


}
