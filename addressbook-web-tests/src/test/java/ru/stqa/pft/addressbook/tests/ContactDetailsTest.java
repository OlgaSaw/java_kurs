package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactsPage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
              .withTitle("Mr").withAddress("szeligowska street").withMobilePhone("+48555888555").withEmail("test@test.pl"), true);
    }
  }

  @Test


  public void testContactDetails(){
    app.goTo().contactsPage();
    ContactData contact = app.contact().allList().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm= app.contact().infoFromDetailsForm(contact);

    assertThat(contactInfoFromDetailsForm.getAllDetails(), equalTo(allDetails(contactInfoFromEditForm)));
  }

  private String allDetails(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getMiddlename(), contact.getLastname(), contact.getNickname(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(),
            contact.getEmail2(), contact.getEmail3()).stream()
            .filter((s)->!s.equals(""))
            .map(ContactDetailsTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","\n");
  }


}
