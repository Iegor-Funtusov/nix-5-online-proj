package business;

import dao.ContactDAO;
import dao.ContactDAOFactory;
import domain.Contact;

import java.util.List;

public class ContactManager {
    private ContactDAO dao;

   public ContactManager() {
        dao = ContactDAOFactory.getContactDAO();
    }

    // Добавление контакта
    public void addContact(Contact contact) {
        dao.addContact(contact);
    }

    // Редактирование контакта
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }

    // Удаление контакта по его ID
    public void deleteContact(int contactId) {
        dao.deleteContact(contactId);
    }

    // Получение одного контакта
   public Contact getContact(int contactId) {
        return dao.getContact(contactId);
    }

    // Получение списка контактов
    public List<Contact> findContacts() {
        return dao.findContacts();
    }
}
