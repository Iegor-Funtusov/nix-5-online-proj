package lib;

public class CrudProcessFactory {
    public static CrudProcess getCrudProcess() {
        return new CrudProcessList();
    }

}
