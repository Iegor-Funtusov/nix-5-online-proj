package ua.com.alevel.app;

import ua.com.alevel.lib.CrudProcess;
import ua.com.alevel.lib.CrudProcessFactory;

import java.util.Collection;

public class TaxiDriverService {

    CrudProcess<TaxiDriver> driverCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

    public void create(TaxiDriver taxiDriver){
        driverCrudProcess.create(taxiDriver);
    }

    public void update(TaxiDriver taxiDriver){
        driverCrudProcess.update(taxiDriver);
    }

    public void delete(String id){
        driverCrudProcess.delete(id);
    }

    public Collection<TaxiDriver> read(){
        return driverCrudProcess.read();
    }

    public TaxiDriver read(String id){
        return driverCrudProcess.read(id);
    }
}
