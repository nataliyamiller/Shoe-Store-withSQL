import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest{
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver(){
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Shoe stores");
  }

  @Test
  public void stylistClientsFormIsDisplayed() {
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Add a new store");
  }

  @Test
  public void storeIsSavedToDatabaseAndDisplayedOnTheStoresPage() {
    Store myStore = new Store("Target", "Portland, OR", "503-555-5555");
    myStore.save();
    String storePath = ("http://localhost:4567/stores");
    goTo(storePath);
    assertThat(pageSource()).contains("Target", "Portland, OR", "503-555-5555");
  }

}
