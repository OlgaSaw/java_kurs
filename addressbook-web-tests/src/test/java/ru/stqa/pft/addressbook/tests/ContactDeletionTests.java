package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase{


  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactsPage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
              .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobile("+48555888555").withEmail("test@test.pl")
              .withHomepage("www.test.pl").withBday("1").withBmonth("January").withByear("1989").withAday("2").withAmonth("March")
              .withAyear("2010").withGroup("test1"), true);
    }
  }
  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
