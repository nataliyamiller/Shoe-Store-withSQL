import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void store_instantiatesCorrectly_true() {
    Store testStore = new Store("Target", "Portland, OR", "503-555-5555");
    assertTrue(testStore instanceof Store);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

}
