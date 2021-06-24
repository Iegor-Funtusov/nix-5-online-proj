package ua.nkrasnovoronka.data;

public class CSVLibraryDB implements LibraryDB {

    private static CSVLibraryDB instance;

    private CSVLibraryDB() {

    }

    public static CSVLibraryDB getInstance() {
        if (instance != null) {
            return instance;
        }
        return new CSVLibraryDB();
    }


}
