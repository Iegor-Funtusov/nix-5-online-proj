package dao;

public class ContactDAOFactory {
    public static ContactDAO getContactDAO() {
        return new ContactImplDAO();
    }
}
