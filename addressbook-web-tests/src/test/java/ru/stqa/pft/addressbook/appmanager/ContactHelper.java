package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
  super(wd);
  }

  public void returnToContacts() {
    click(By.linkText("home"));
  }

  public void submit() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle() );
    type(By.name("company"), contactData.getCompany() );
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile() );
    type(By.name("email"), contactData.getEmail() );
    type(By.name("homepage"), contactData.getHomepage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
    type(By.name("byear"), contactData.getByear() );
    click(By.name("aday"));
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
    click(By.xpath("//div[@id='content']/form/select[3]/option[4]"));
    click(By.name("amonth"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
    click(By.xpath("//div[@id='content']/form/select[4]/option[4]"));
    type(By.name("ayear"), contactData.getAyear() );
    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void modifyContact(List<ContactData> before, ContactData contact) {
    initContactModification(before.size()-1);
    fillContactForm(contact, false);
    submitContactModification();
    returnToContacts();
  }

  public void deleteContact(int index) {
    selectContact(index);
    deleteContacts();
    submitContactDeletion();
    returnToContacts();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm (contact, true);
    submit();
    returnToContacts();
    }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      ContactData contact = new ContactData(id, firstname, null, lastname,null, null, null, null, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}

