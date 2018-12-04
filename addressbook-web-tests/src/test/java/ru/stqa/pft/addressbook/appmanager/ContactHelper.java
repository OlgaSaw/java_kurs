package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.tests.GroupContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
  super(wd);
  }

  public void returnToContacts() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submit() {
    click(By.xpath("//div[@id='content']/form/select[5]/option[2]"));
  }

  public void fillContactForm(GroupContactData groupContactData) {
    type(By.name("firstname"),groupContactData.getFirstname());
    type(By.name("middlename"),groupContactData.getMiddlename());
    type(By.name("lastname"),groupContactData.getLastname());
    type(By.name("nickname"),groupContactData.getNickname());
    type(By.name("title"),groupContactData.getTitle() );
    type(By.name("company"),groupContactData.getCompany() );
    type(By.name("address"), groupContactData.getAddress());
    type(By.name("mobile"),groupContactData.getMobile() );
    type(By.name("email"),groupContactData.getEmail() );
    type(By.name("homepage"),groupContactData.getHomepage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(groupContactData.getBday());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(groupContactData.getBmonth());
    type(By.name("byear"),groupContactData.getByear() );
    click(By.name("aday"));
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(groupContactData.getAday());
    click(By.xpath("//div[@id='content']/form/select[3]/option[4]"));
    click(By.name("amonth"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(groupContactData.getAmonth());
    click(By.xpath("//div[@id='content']/form/select[4]/option[4]"));
    type(By.name("ayear"),groupContactData.getAyear() );


  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }
}
