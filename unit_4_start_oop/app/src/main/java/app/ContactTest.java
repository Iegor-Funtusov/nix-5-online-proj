package app;

import java.util.Collection;

public class ContactTest {
    public static void main(String[] args) {
        System.out.println("ContactTest.main");

       ContactService contactService = new ContactService();

        Contact contact = new Contact();
        contact.setFirstName("Михаил");
        contact.setLastName("Михайлов");
        contact.setPhone("+380-50-123-456");
        contact.setEmail("mik@mail.ru");
        contactService.create(contact);

        contact = new Contact();
        contact.setFirstName("Иван");
        contact.setLastName("Василенко");
        contact.setEmail("iv@ukr.net");
        contact.setPhone("+380-50-312-645");
        contactService.create(contact);

        Collection<Contact> list = contactService.read();
        list.forEach(System.out::println);

        for (Contact c: list) {
            if (c.getFirstName().equals("Михаил")) {
                c.setFirstName("Миша");
                contactService.update(c);
            }
        }

        list = contactService.read();
        list.forEach(System.out::println);

    }
}
