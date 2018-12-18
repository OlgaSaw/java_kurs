package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactsPage();
    if (app.contact().list().size()==0){
      app.contact().create(new ContactData("Test", "Test1", "Test2",
              "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555",
              "test@test.pl", "www.test.pl", "1", "January", "1989",
              "2", "March", "2010", "test1"), true);
    }
  }
  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData(before.get(index).getId(),"Test", "Test1", "Test2", "ttt", "Mr", "Bravura", "szeligowska street", "+48555888555", "test@test.pl", "www.test.pl", "1", "January", "1989", "2", "March", "2010", null);
    app.contact().modify(before, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
