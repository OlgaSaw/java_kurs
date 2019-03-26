package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions1() {

    if (app.db().contacts().size() == 0) {
      app.goTo().contactsPage();
      app.contact().initContactCreation();
      app.contact().fillContactForm(new ContactData().withFirstname("Test11").withLastname("Test11"), true);
    }
  }

  @BeforeMethod

  public void ensurePreconditions2() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactAddingToGroup() {
    app.goTo().contactsPage();
    Integer id=app.contact().selectContactAndReturnID(0);
    ContactData contact=app.db().getContact(id);
    Groups groups=app.db().groups();

    List<GroupData> available = new ArrayList<>();
    available.addAll(groups);
    available.removeAll(contact.getGroups());

    if(available.size()>0){
      app.contact().selectGroupAdd(available.get(0).getName());
      app.contact().click(By.name("add"));
      assertThat(app.db().getContact(contact.getId()).getGroups(),
              equalTo(contact.getGroups().withAdded(available.get(0))));
    }
    else{
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newgroup "+new Date()));
      testContactAddingToGroup();
    }
  }



}