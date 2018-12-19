package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.goTo().contactsPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
            .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobile("+48555888555").withEmail("test@test.pl")
            .withHomepage("www.test.pl").withBday("1").withBmonth("January").withByear("1989").withAday("2").withAmonth("March")
            .withAyear("2010").withGroup("test1");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }


}
