import Configs.PreStartAppConfig;
import Controllers.MainController;
import Controllers.UserInterface;

import java.net.UnknownServiceException;

public class Application {
    public static void run(){
        PreStartAppConfig.configureApplication();

        UserInterface userInterface = new UserInterface();
        userInterface.userUI();
    }
}
