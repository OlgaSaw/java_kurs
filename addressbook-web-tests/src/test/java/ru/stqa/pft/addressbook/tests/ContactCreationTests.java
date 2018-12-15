package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactsPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1"), true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);
    app.logout();
  }


}
