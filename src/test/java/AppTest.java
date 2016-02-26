import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#stylistName").with("Stylist 1");
    submit(".btn-primary");
    assertThat(pageSource()).contains("Stylist 1");
  }

  @Test
  public void clientIsCreatedTest() {
    goTo("http://localhost:4567/");
    Stylist myStylist = new Stylist("Stylist 1");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Stylist 1");
    Client clients = new Client("Client 1", myStylist.getId());
    clients.save();
    goTo(stylistPath);
    assertThat(pageSource()).contains("Client 1");
  }

  @Test
  public void stylistIsDeletedTest() {
    String path = "http://localhost:4567/";
    goTo(path);
    Stylist myStylist = new Stylist("Stylist 1");
    myStylist.save();
    goTo(path);
    myStylist.deleteStylist(myStylist.getId());
    goTo(path);
    assertThat((pageSource()).contains("Stylist 1") == false);
  }

  @Test
  public void clientIsDeletedTest() {
    goTo("http://localhost:4567/");
    Stylist myStylist = new Stylist("Stylist 1");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Stylist 1");
    Client clients = new Client("Client 1", myStylist.getId());
    clients.save();
    goTo(stylistPath);
    assertThat(pageSource()).contains("Client 1");
    clients.delete(clients.getId());
    goTo(stylistPath);
    assertThat((pageSource()).contains("Client 1") == false);
  }
}
