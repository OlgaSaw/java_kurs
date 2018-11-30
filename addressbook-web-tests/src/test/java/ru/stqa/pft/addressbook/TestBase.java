package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestBase {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  protected void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  protected void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  protected void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  protected void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
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

  protected void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  protected void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  protected void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  protected void returnToContacts() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  protected void submit() {
    wd.findElement(By.xpath("//div[@id='content']/form/select[5]/option[2]")).click();
  }

  protected void fillContactForm(GroupContactData groupContactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(groupContactData.getFirstname());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(groupContactData.getMiddlename());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(groupContactData.getLastname());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(groupContactData.getNickname());
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(groupContactData.getTitle());
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(groupContactData.getCompany());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(groupContactData.getAddress());
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(groupContactData.getMobile());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(groupContactData.getEmail());
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys(groupContactData.getHomepage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(groupContactData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(groupContactData.getBmonth());
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(groupContactData.getByear());
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(groupContactData.getAday());
    wd.findElement(By.xpath("//div[@id='content']/form/select[3]/option[4]")).click();
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(groupContactData.getAmonth());
    wd.findElement(By.xpath("//div[@id='content']/form/select[4]/option[4]")).click();
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys(groupContactData.getAyear());
    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupContactData.getNewgroup());
  }

  protected void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }


  protected void deleteContacts() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }


  protected void selectContact() {
    wd.findElement(By.name("selected[]")).click();
  }

  protected void goToContactsPage() {
    wd.findElement(By.linkText("home")).click();
  }
}
