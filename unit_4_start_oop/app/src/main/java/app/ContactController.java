package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

public class ContactController {

    ContactService contactService = new ContactService();

    public void readConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в менеджер контактов!");
        System.out.println("Выберите действие или нажмите 'q' для выхода: ");
        System.out.println("C - Create (создание контакта)");
        System.out.println("R - Read (просмотр контактов)");
        System.out.println("U - Update (обновление контакта)");
        System.out.println("F - Find (поиск контакта)");
        System.out.println("D - Delete (удаление контакта)");
        String input = reader.readLine();
        while (!"q".equals(input)) {
            switch (input) {
                case "C":
                    create(reader);
                    break;
                case "R":
                    readAll(reader);
                    break;
                case "U":
                    update(reader);
                    break;
                case "F":
                    find(reader);
                    break;
                case "D":
                    delete(reader);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный выбор!");
                }
            System.out.println("Выберите действие или нажмите 'q' для выхода: ");
            System.out.println("C - Create (создание контакта)");
            System.out.println("R - Read (просмотр контактов)");
            System.out.println("U - Update (обновление контакта)");
            System.out.println("F - Find (поиск контакта)");
            System.out.println("D - Delete (удаление контакта)");
            input = reader.readLine();
            }
        }

    private void create(BufferedReader reader) throws IOException {
        Contact contact = new Contact();
        System.out.println("Введите имя контакта:");
        String name = reader.readLine();
        contact.setFirstName(name);
        System.out.println("Введите фамилию:");
        String surname = reader.readLine();
        contact.setLastName(surname);
        System.out.println("Введите номер телефона:");
        String phone = reader.readLine();
        contact.setPhone(phone);
        System.out.println("Введите e-mail:");
        String email = reader.readLine();
        contact.setEmail(email);
        contactService.create(contact);
    }

    private void readAll(BufferedReader reader) {
        System.out.println("Просмотр контактов:");
        Collection<Contact> list = contactService.read();
        list.forEach(System.out::println);
    }

    private void update(BufferedReader reader) throws IOException {
        Collection<Contact> list = contactService.read();
        System.out.println("Введите фамилию контакта для обновления: ");
        String name = reader.readLine();
        for (Contact contact : list) {
            if (contact.getLastName().equals(name)) {
                System.out.println("Выберите действие или нажмите 'q' для выхода: ");
                System.out.println("1 - изменение имени контакта");
                System.out.println("2 - изменение фамилии");
                System.out.println("3 - изменение телефона");
                System.out.println("4 - изменение e-mail");
                String input = reader.readLine();
                while (!"q".equals(input)) {
                    switch (input) {
                        case "1":
                            System.out.println("Введите новое имя контакта:");
                            contact.setFirstName(reader.readLine());
                            break;
                        case "2":
                            System.out.println("Введите новую фамилию:");
                            contact.setLastName(reader.readLine());
                            break;
                        case "3":
                            System.out.println("Введите новый телефон:");
                            contact.setPhone(reader.readLine());
                            break;
                        case "4":
                            System.out.println("Введите новый e-mail:");
                            contact.setEmail(reader.readLine());
                            break;
                        default:
                            throw new IllegalArgumentException("Неизвестный выбор!");
                    }
                    contactService.update(contact);
                    break;
                }
            }
        }
    }

    private void delete(BufferedReader reader) throws IOException {
        Collection<Contact> list = contactService.read();
        System.out.println("Введите фамилию контакта для удаления: ");
        String name = reader.readLine();

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Contact contact = (Contact) iterator.next();
            if (contact.getLastName().equals(name)) {
               // iterator.remove();
                contactService.delete(contact.getId());
                   break;
            }
        }
    }

    private void find(BufferedReader reader) throws IOException {
        Collection<Contact> list = contactService.read();
        System.out.println("Введите фамилию контакта для поиска: ");
        String name = reader.readLine();
        for (Contact contact : list) {
            if (contact.getLastName().equals(name)) {
                System.out.println(contactService.read(contact.getId()));
            }
        }
    }
}

