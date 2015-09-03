import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;


public class Brand {
  private String brand_name;
  private String style;
  private String type;
  private String color;
  private int id;


  public String getBrandName() {
    return brand_name;
  }

  public String getStyle() {
    return style;
  }

  public String getType() {
    return type;
  }

  public String getColor() {
    return color;
  }

  public int getId() {
    return id;
  }

  public Brand (String brandName, String style, String type, String color) {
    this.brand_name = brandName;
    this.style = style;
    this.type = type;
    this.color = color;
  }

  @Override
  public boolean equals(Object otherBrand) {
    if(!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getBrandName().equals(newBrand.getBrandName()) &&
             this.getStyle().equals(newBrand.getStyle()) &&
             this.getType().equals(newBrand.getType()) &&
             this.getColor().equals(newBrand.getColor()) &&
             this.getId() == newBrand.getId();
    }
  }

  public static List<Brand> all() {
    String sql = "SELECT * FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands (brand_name, style, type, color) VALUES (:brand_name, :style, :type, :color)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("brand_name", this.brand_name)
      .addParameter("style", this.style)
      .addParameter("type", this.type)
      .addParameter("color", this.color)
      .executeUpdate()
      .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands WHERE id=:id";
      Brand brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }

  public void update(String brand_name, String style, String type, String color) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE brands SET brand_name = :brand_name, style = :style, type = :type, color = :color WHERE id = :id";
      con.createQuery(sql)
        .addParameter("brand_name", brand_name)
        .addParameter("style", style)
        .addParameter("type", type)
        .addParameter("color", color)
        .addParameter("id", id)
        .executeUpdate();
        }
      }



}
