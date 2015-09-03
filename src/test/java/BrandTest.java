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
}
