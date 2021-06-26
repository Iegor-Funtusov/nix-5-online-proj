package CrudCSV;
import DataClasses.Author;
import FileWork.Constants;
import FileWork.FileWorker;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorCrudProcess<A> implements CrudProcess<DataClasses.Author>{
    private String PATH;

    public AuthorCrudProcess(String path) {
        this.PATH = path;
    }


    @Override
    public void create(Author authorToAdd) {
        isEmptyPath();
        List<String[]> csvData = FileWorker.readFromFile(PATH);

        if(csvData.isEmpty()){
            csvData.add(generateHeader());
        }

        if(authorToAdd.getId() == null){
            String id = generateID(UUID.randomUUID().toString(), read());
            authorToAdd.setId(id);
        }

        String[] bookData = Decorators.FormatAuthor.fromAuthorToStringArr(authorToAdd);
        csvData.add(bookData);
        FileWorker.writeToFile(PATH, csvData);
    }


    @Override
    public void update(Author authorToUpd) {
        isEmptyPath();

        List<String[]> csvData = FileWorker.readFromFile(PATH);
        for(int i = 0; i < csvData.size(); i++){
            String []authorData = csvData.get(i);

            if(authorData[Constants.ID].equals(authorToUpd.getId())){
                String []authorInString = Decorators.FormatAuthor.fromAuthorToStringArr(authorToUpd);
                csvData.set(i, authorInString);
                FileWorker.writeToFile(PATH, csvData);
                return;
            }
        }

        throw new RuntimeException("Author does not exist");
    }


    @Override
    public void delete(String id) {
        isEmptyPath();
        List<String[]> csvData = FileWorker.readFromFile(PATH);
        for(String[] authorData : csvData){
            if(authorData[Constants.ID].equals(id) && !authorData[Constants.AUTHOR_VISIBLE].equals("false")){
                authorData[Constants.AUTHOR_VISIBLE] = "false";
                FileWorker.writeToFile(PATH, csvData);
                return;
            }
        }
        throw new RuntimeException("Author does not exist");
    }


    @Override
    public List<Author> read() {
        isEmptyPath();

        List<String[]> csvData = FileWorker.readFromFile(PATH);
        List<Author> authors = new ArrayList<>();

        for(int i = 0; i < csvData.size(); i++){
            String[] data = csvData.get(i);
            //Если визибл флаг не опущен
            if(!data[Constants.AUTHOR_VISIBLE].equals("false")){
                Author author = Decorators.FormatAuthor.fromStringArrToAuthor(data);
                authors.add(author);
            }
        }

        return authors;
    }


    @Override
    public Author read(String id) {
        isEmptyPath();
        List<Author> authors =  read();
        for(Author item : authors){
            if(item.getId().equals(id)){
                return item;
            }
        }

        throw new RuntimeException("Author does not exist");
    }


    public String getPATH() {
        isEmptyPath();
        return PATH;
    }


    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    private void isEmptyPath(){
        if(!StringUtils.isNotBlank(PATH)){
            throw new RuntimeException("Path is empty");
        }
    }

    private String generateID(String id, List<Author> authors){
        if(authors.stream().anyMatch(e -> e.getId().equals(id))){
            return generateID(UUID.randomUUID().toString(), authors);
        }
        return id;
    }

    private String[] generateHeader(){
        String []header = new String[Constants.AUTHOR_FIELDS_QUANTITY];
        header[Constants.ID] = "id";
        header[Constants.AUTHOR_FIRSTNAME] = "firstname";
        header[Constants.AUTHOR_LASTNAME] = "lastname";
        header[Constants.AUTHOR_BOOKS] = "books";
        header[Constants.AUTHOR_VISIBLE] = "visible";
        return header;
    }
}