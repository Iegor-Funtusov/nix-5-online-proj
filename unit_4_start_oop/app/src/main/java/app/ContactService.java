package app;

import lib.CrudProcess;
import lib.CrudProcessFactory;

import java.util.Collection;

public class ContactService {
    CrudProcess<Contact> crudProcess = CrudProcessFactory.getCrudProcess();

    public void create(Contact contact) {
        crudProcess.create(contact);
    }

    public void update(Contact contact) {
        crudProcess.update(contact);
    }

    public void delete(String id) {
        crudProcess.delete(id);
    }

    public Collection<Contact> read() {
        return crudProcess.read();
    }

    public Contact read(String id) {
        return crudProcess.read(id);
    }
}
