package dao;

import domain.Contact;

import java.util.List;

public interface ContactDAO {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(int contactId);
    public Contact getContact(int contactId);
    public List<Contact> findContacts();
}
