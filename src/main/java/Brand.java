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


}
