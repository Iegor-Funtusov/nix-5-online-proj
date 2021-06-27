import Configs.PreStartAppConfig;
import Controllers.UserInterface;

public class Application {
    public static void run(){
        PreStartAppConfig.configureApplication();

        UserInterface userInterface = new UserInterface();
        userInterface.userUI();
    }
}
