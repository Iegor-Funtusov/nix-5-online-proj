package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.Relation;
import ua.com.alevel.lib.CrudService;
import ua.com.alevel.lib.CrudServiceFactory;

public class RelationService {

    private final CrudService<Relation> relationCrudService = CrudServiceFactory.getInstance().getCrudService();
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public void create(Relation relation){
        loggerInfo.info("Start creating the relation: " + relation.getBook() + " - " + relation.getAuthor());
        relationCrudService.create(relation);
        loggerInfo.info("The relation was created");
    }

    public void update(Relation relation){
        loggerInfo.info("Start updating the relation with id: " + relation.getId());
        try{
            relationCrudService.update(relation);
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerInfo.info("The relation was updated.");
    }

    public void delete(Relation relation) {
        if (relation == null) {
            loggerError.error("relation is null");
            throw new RuntimeException("relation is null");
        }
        loggerWarn.warn("Start deleting relation by id: " + relation.getId());
        try{
            relationCrudService.delete(relation.getId());
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
        loggerWarn.warn("The relation was deleted.");
    }

    public Relation read(String id){
        loggerInfo.info("Start reading the relation by id: " + id);
        try{
            Relation relation = relationCrudService.read(id);
            loggerInfo.info("The relation was read.");
            return relation;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }

    }

    public Object[] read(){
        try{
            loggerInfo.info("Start reading all relations.");
            Object[] relations = relationCrudService.read();
            loggerInfo.info("Relations were read");
            return relations;
        }catch (RuntimeException e){
            loggerError.error(e.getMessage());
            throw e;
        }
    }

}
