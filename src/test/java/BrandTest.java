import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.ArrayList;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void brand_instantiatesCorrectly_true() {
    Brand testBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    assertTrue(testBrand instanceof Brand);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void equals_returnTrueIfBrandsAreTheSame() {
    Brand firstBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    Brand secondBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void save_savesBrandIntoDatabase_true() {
    Brand myBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();
    assertTrue(Brand.all().get(0).equals(myBrand));
  }

  @Test
  public void find_findsBrandInDatabase_true() {
    Brand myBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertTrue(myBrand.equals(savedBrand));
  }

  @Test
  public void update_changesStoreNameAddressAndPhoneNumberInDatabase_true() {
    Brand myBrand = new Brand ("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();
    String brandName = "Adidas";
    String style = "Casual";
    String type = "Men's";
    String color = "black";
    myBrand.update(brandName, style, type, color);
    assertTrue(Brand.all().get(0).getBrandName().equals(brandName));
    assertTrue(Brand.all().get(0).getStyle().equals(style));
    assertTrue(Brand.all().get(0).getType().equals(type));
    assertTrue(Brand.all().get(0).getColor().equals(color));
  }

  @Test
  public void addStore_addsStoreToBrand() {
    Store myStore = new Store("Target", "Portland, OR", "503-555-5555");
    myStore.save();

    Brand myBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();

    myBrand.addStore(myStore);
    Store savedStore = myBrand.getStores().get(0);
    assertTrue(myStore.equals(savedStore));
 }


}
