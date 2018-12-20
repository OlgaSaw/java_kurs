package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Test").withMiddlename("Test1").withLastname("Test2")
            .withNickname("ttt").withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobile("+48555888555")
            .withEmail("test@test.pl").withHomepage("www.test.pl").withBday("1").withBmonth("January").withByear("1989")
            .withAday("2").withAmonth("March").withAyear("2010");

    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
