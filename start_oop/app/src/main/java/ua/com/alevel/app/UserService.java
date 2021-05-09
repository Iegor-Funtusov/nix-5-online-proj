package ua.com.alevel.app;

import ua.com.alevel.lib.CrudProcess;
import ua.com.alevel.lib.CrudProcessFactory;

import java.util.Collection;

public class UserService {

    CrudProcess<User> userCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

    public void create(User user){
        userCrudProcess.create(user);
    }

    public void update(User user){
        userCrudProcess.update(user);
    }

    public void delete(String id){
        userCrudProcess.delete(id);
    }

    public Collection<User> read(){
        return userCrudProcess.read();
    }

    public User read(String id){
        return userCrudProcess.read(id);
    }
}
