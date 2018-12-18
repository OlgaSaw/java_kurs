package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToContactsPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Test", "Test1", "Test2",
              "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555",
              "test@test.pl", "www.test.pl", "1", "January", "1989",
              "2", "March", "2010", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteContacts();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
