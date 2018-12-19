package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.goTo().contactsPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
            .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobile("+48555888555").withEmail("test@test.pl")
            .withHomepage("www.test.pl").withBday("1").withBmonth("January").withByear("1989").withAday("2").withAmonth("March")
            .withAyear("2010").withGroup("test1");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals (before, after);

  }


}
