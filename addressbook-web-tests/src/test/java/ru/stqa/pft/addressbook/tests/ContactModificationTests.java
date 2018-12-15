package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToContactsPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Test", "Test1", "Test2",
              "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555",
              "test@test.pl", "www.test.pl", "1", "January", "1989",
              "2", "March", "2010", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    app.getContactHelper().fillContactForm(new ContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());
  }
}
