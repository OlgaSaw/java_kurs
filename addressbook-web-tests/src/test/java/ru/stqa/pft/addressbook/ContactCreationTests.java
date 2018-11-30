package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String login, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(login);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.id("LoginForm")).submit();
  }

  @Test
  public void testContactCreation() throws Exception {

    initContactCreation();
    fillContactForm("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1");
    submit();
    returnToContacts();
    logout();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private void returnToContacts() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void submit() {
    wd.findElement(By.xpath("//div[@id='content']/form/select[5]/option[2]")).click();
  }

  private void fillContactForm(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String mobile, String email, String homepage, String bday, String bmonth, String byear, String aday, String amonth, String ayear, String newgroup) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(firstname);
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(middlename);
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(lastname);
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(nickname);
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(title);
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(company);
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(address);
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(mobile);
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(email);
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys(homepage);
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bday);
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bmonth);
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(byear);
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(aday);
    wd.findElement(By.xpath("//div[@id='content']/form/select[3]/option[4]")).click();
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(amonth);
    wd.findElement(By.xpath("//div[@id='content']/form/select[4]/option[4]")).click();
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys(ayear);
    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newgroup);
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();


  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
