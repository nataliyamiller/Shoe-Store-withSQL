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

  @Test
  public void equals_returnTrueIfStoresAreTheSame() {
    Store firstStore = new Store("Target", "Portland, OR", "503-555-5555");
    Store secondStore = new Store("Target", "Portland, OR", "503-555-5555");
    assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void save_savesStoreIntoDatabase_true() {
    Store myStore = new Store("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    assertTrue(Store.all().get(0).equals(myStore));
  }

  @Test
  public void find_findsStoreInDatabase_true() {
    Store myStore = new Store("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    Store savedStore = Store.find(myStore.getId());
    assertTrue(myStore.equals(savedStore));
  }

  @Test
  public void updateName_changesStoreNameInDatabase() {
    Store myStore = new Store ("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    String name = "Ross";
    myStore.updateName(name);
    assertTrue(Store.all().get(0).getName().equals(name));
    assertEquals(Store.all().get(0).getAddress(), "Portland, OR");
    assertEquals(Store.all().get(0).getPhoneNumber(), "503-555-5555");
  }

  @Test
  public void updateAddress_changesStoreAddressInDatabase() {
    Store myStore = new Store ("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    String address = "Bend, OR";
    myStore.updateAddress(address);
    assertEquals(Store.all().get(0).getName(), "Target");
    assertTrue(Store.all().get(0).getAddress().equals(address));
    assertEquals(Store.all().get(0).getPhoneNumber(), "503-555-5555");
  }

  @Test
  public void updatePhoneNumber_changesStorePhoneNumberInDatabase() {
    Store myStore = new Store ("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    String phoneNumber = "971-222-5555";
    myStore.updatePhoneNumber(phoneNumber);
    assertEquals(Store.all().get(0).getName(), "Target");
    assertEquals(Store.all().get(0).getAddress(), "Portland, OR");
    assertTrue(Store.all().get(0).getPhoneNumber().equals(phoneNumber));
  }

  @Test
  public void addBrand_addsBrandToStore() {
    Store myStore = new Store("Target", "Portland, OR", "503-555-5555");
    myStore.save();

    Brand myBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();

    myStore.addBrand(myBrand);
    Brand savedBrand = myStore.getBrands().get(0);
    assertTrue(myBrand.equals(savedBrand));
 }

  @Test
  public void delete_deletesAllStoresAndListAssociation () {
    Store myStore = new Store ("Target", "Portland, OR", "503-555-5555");
    myStore.save();

    Brand myBrand = new Brand("Nike", "Sport", "Women's", "Yellow");
    myBrand.save();

    myStore.addBrand(myBrand);
    myStore.delete();
    assertEquals(myBrand.getStores().size(), 0);
  }

}
