import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        UserController userController = new UserController();
        try{
            userController.userInterface();
        }
        catch (IOException exception){
            exception.getStackTrace();
        }
    }
}