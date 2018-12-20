package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactsPage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
              .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobilePhone("+48555888555").withEmail("test@test.pl")
              .withHomepage("www.test.pl").withBday("1").withBmonth("January").withByear("1989").withAday("2").withAmonth("March")
              .withAyear("2010").withGroup("test1"), true);
    }
  }

  @Test

  public void testContactPhones(){
    app.goTo().contactsPage();
    ContactData contact = app.contact().allList().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
    assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));

  }
}
