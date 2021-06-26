package ua.com.ionio;

import ua.com.ionio.services.Controller;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.run();

//        CreateFile createFile = new CreateFile();
//        createFile.delete();
//        createFile.init();
//        List<String> strings = new ArrayList<>();
//        List<String> strings2 = new ArrayList<>();
//        strings.add("dfdfdf");
//        strings.add("dfdfdaa");
//        Author author = new Author("Richard", "Suo", strings);
//        Author author1 = new Author("Richard2_________", "Suo2", strings2);
//        AccessService access = new AccessService();
//        //DaoAuthor daoAuthor = new DaoAuthor();
//        access.createAuthor(author);
//        access.createAuthor(author1);
//        System.out.println(access.getAuthorById("2"));
//        access.deleteAuthor("2");
//        System.out.println("*****************");
//        System.out.println(access.getAllAuthors());
//        Author author2 = access.getAuthorById("2");
//        author2.setLastname("UPDATES");
//        access.updateAuthor(author2);
    }
}
