package dao;

import domain.Contact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ContactImplDAO implements ContactDAO {

    private final List<Contact> contacts = new ArrayList<Contact>();

    public void addContact(Contact contact) {
        contact.setContactId(contact.getContactId());
        contacts.add(contact);
    }

  public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getContactId());
        if(oldContact != null) {
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());
        }
    }

  public void deleteContact(int contactId) {
        for (Iterator<Contact> it = contacts.iterator(); it.hasNext();) {
            Contact cnt = it.next();
            if (Objects.equals(cnt.getContactId(), contactId)) {
            it.remove();
            }
        }
    }

   public Contact getContact(int contactId) {
        for (Contact contact : contacts) {
            if (Objects.equals(contact.getContactId(), contactId)) {
                return contact;
            }
            return null;
        }

        return null;
    }

    public List<Contact> findContacts() {
        return contacts;
    }

}
