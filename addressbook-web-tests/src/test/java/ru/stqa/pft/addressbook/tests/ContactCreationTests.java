package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() {
    app.goTo().contactsPage();
    app.contact().initContactCreation();
    File photo= new File("src/test/resources/160.png");
    app.contact().fillContactForm(
            new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
            .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobilePhone("+48555888555")
            .withEmail("test@test.pl").withPhoto(photo), true);
    app.contact().submit();
    app.contact().returnToContacts();
  }

//  @Test
//  public void testCurrentDir(){
//    File currentDir= new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo= new File("src/test/resources/160.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }


}
