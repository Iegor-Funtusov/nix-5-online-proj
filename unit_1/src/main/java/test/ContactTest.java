package test;

import business.ContactManager;
import domain.Contact;

import java.util.List;

public class ContactTest {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Contact c1 = new Contact(1,"Андрей", "Соколов", "+380-50-123-456", "sokolov@ukr.net");
        Contact c2 = new Contact(2, "Сергей", "Иванов", "380-93-123-678", "ivanov@gmail.com");
        Contact c3 = new Contact(3, "Татьяна", "Семёнова", "+380-67-123-1234", "semenova@ukr.net");

        System.out.println("Добавление контакта ");
        contactManager.addContact(c1);
        contactManager.addContact(c2);
        contactManager.addContact(c3);
        System.out.println("Список контактов: ");
        List<Contact> contacts = contactManager.findContacts();
        for (Contact c : contacts) {
            System.out.println(c);
        }
        System.out.println();

        System.out.println("Получение контакта ");
        Contact contact = contactManager.getContact(1);
        System.out.println(contact);
        System.out.println();

        System.out.println("Удаление контакта ");
        contactManager.deleteContact(1);
        System.out.println("Список контактов: ");
        contacts = contactManager.findContacts();
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }
}
