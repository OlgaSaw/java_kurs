package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactCreationTests extends TestBase{
  Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null){
        xml += line;
        line= reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line= reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //List<ContactData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    logger.info("Start test testGroupCreation");
    app.goTo().contactsPage();
    Contacts before=app.db().contacts();
    app.contact().initContactCreation();
    app.contact().fillContactForm(contact, true);
    app.contact().submit();
    app.contact().returnToContacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
    logger.info("Stop test testGroupCreation");
  }

//  @Test
//  public void testContactCreation() {
//    logger.info("Start test testGroupCreation");
//    app.goTo().contactsPage();
//    app.contact().initContactCreation();
//    File photo= new File("src/test/resources/160.png");
//    app.contact().fillContactForm(
//            new ContactData().withFirstname("Test").withMiddlename("Test1").withLastname("Test2").withNickname("ttt")
//            .withTitle("Mr").withCompany("Bravura").withAddress("szeligowska street").withMobilePhone("+48555888555")
//            .withEmail("test@test.pl").withPhoto(photo), true);
//    app.contact().submit();
//    app.contact().returnToContacts();
//    logger.info("Stop test testGroupCreation");
//  }

//  @Test
//  public void testCurrentDir(){
//    File currentDir= new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo= new File("src/test/resources/160.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }


}
