package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactsPage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
              .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobilePhone("+48555888555").withEmail("test@test.pl")
              .withHomepage("www.test.pl"), true);
    }
  }

  @Test

  public void testContactPhones(){
    app.goTo().contactsPage();
    ContactData contact = app.contact().allList().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
            .filter((s)->!s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));


  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
