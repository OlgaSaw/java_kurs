package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new GroupContactData("Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", "test1"));
    submit();
    returnToContacts();
    logout();
  }


}
