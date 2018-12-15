package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToContactsPage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Test", "Test1", "Test2",
              "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555",
              "test@test.pl", "www.test.pl", "1", "January", "1989",
              "2", "March", "2010", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContacts();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToContacts();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
  }


}
