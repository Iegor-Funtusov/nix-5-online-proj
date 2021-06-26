package Validation;

import CrudCSV.BaseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Validator {

    public static void validateIndexOfElement(int index, List<? extends BaseEntity> elements){
        int size = elements.size();
        if(index < 0 || index > (size - 1)){
            throw new RuntimeException("Incorrect index entered");
        }
    }


    public static void isNotNullEntity(BaseEntity entityToCheck){
        if(entityToCheck == null){
            throw new RuntimeException("The entity does not exist");
        }
    }


    public static void isExistFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (!filePath.toFile().exists()) {
            Files.createFile(filePath);
        }
    }
}
