package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactsPage();
    int before=app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before + 1);
    app.logout();
  }


}
