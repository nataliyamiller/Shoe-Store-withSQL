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
}
