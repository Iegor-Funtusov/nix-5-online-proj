package ua.com.alevel.service;

import ua.com.alevel.entity.Relation;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

public class RelationService {

    private final CrudService<Relation> relationCrudService = CrudServiceFactory.getInstance().getCrudService();

    public void create(Relation relation){
        relationCrudService.create(relation);
    }

    public void update(Relation relation){
        relationCrudService.update(relation);
    }

    public void delete(Relation relation) {
        if (relation == null) {
            throw new RuntimeException("relation is null");
        }
        relationCrudService.delete(relation.getId());
    }

    public Relation read(String id){
        return relationCrudService.read(id);
    }

    public Object[] read(){
        return relationCrudService.read();
    }

}
