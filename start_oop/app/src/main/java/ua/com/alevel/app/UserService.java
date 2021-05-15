package ua.com.alevel.app;

import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

import java.util.Collection;

public class UserService {

    CrudService<User> userCrudService = CrudServiceFactory.getInstance().getCrudService();

    public void create(User user){
        userCrudService.create(user);
    }

    public void update(User user){
        userCrudService.update(user);
    }

    public void delete(String id){
        userCrudService.delete(id);
    }

    public User read(String id){
        return userCrudService.read(id);
    }

    public Collection<User> read(){
       return userCrudService.read();
    }
}
