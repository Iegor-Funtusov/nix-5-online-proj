import domain.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tests {

 Contact contact = new Contact();

 @Before
 public void setUp() {
     contact.setContactId(1);
     contact.setFirstName("Андрей");
     contact.setLastName("Соколов");
     contact.setPhone("+380-50-123-456");
     contact.setEmail("sokolov@ukr.net");

 }

  @Test
    public void addContact() {
      Assert.assertEquals(1, contact.getContactId());
      Assert.assertEquals("Андрей", contact.getFirstName());
      Assert.assertEquals("Соколов", contact.getLastName());
      Assert.assertEquals("+380-50-123-456", contact.getPhone());
      Assert.assertEquals("sokolov@ukr.net", contact.getEmail());

  }
}
