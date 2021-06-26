package CrudCSV;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public interface CrudProcess <E extends BaseEntity>{
    void create(E e) throws IOException, CsvException ;
    void update(E e) throws IOException, CsvException ;
    void delete(String id) throws IOException, CsvException ;
    List<E> read() throws IOException, CsvException;
    E read(String id) throws IOException, CsvException;
}
