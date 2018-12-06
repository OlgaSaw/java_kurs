package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToContactsPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContacts();
  }
}
