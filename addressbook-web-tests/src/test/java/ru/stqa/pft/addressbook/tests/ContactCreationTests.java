package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactsPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);

    int max = 0;
    for (ContactData c: after) {
      if (c.getId()>max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));


    app.logout();
  }


}
