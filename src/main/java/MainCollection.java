import java.io.IOException;

public class MainCollection {
    public static void main(String[] args) {
        UserControll userControll = new UserControll();
        try {
            userControll.execute();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
