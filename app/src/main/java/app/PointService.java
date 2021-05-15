package app;

import lib.CrudProcess;
import lib.CrudProcessFactory;

import java.util.Collection;

public class PointService {
    CrudProcess<Point> pointCrudProcess = CrudProcessFactory.getInstance().getCrudProcess();

    public void create(Point point){
        pointCrudProcess.create(point);
    }

    public void update(Point point){
        pointCrudProcess.update(point);
    }

    public void delete(String id){
        pointCrudProcess.delete(id);
    }

    public Collection<Point> read(){
        return pointCrudProcess.read();
    }

    public Point read(String id){
        return pointCrudProcess.read(id);
    }


    public Point isExist(String nameOfPoint){
        for(Point p : read()){
            if(p.getName().equals(nameOfPoint)){
                return p;
            }
        }
        return null;
    }

}
