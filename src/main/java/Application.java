import Configs.PreStartAppConfig;
import Controller.TaskController;

public class Application {
    public static void run(){
        PreStartAppConfig.configureApp();
        TaskController.userUI();
    }
}
