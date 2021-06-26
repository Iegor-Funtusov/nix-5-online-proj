import Configs.PreStartAppConfig;
import Controllers.MainController;

public class Application {
    public static void run(){
        PreStartAppConfig.configureApplication();

        MainController mainController = new MainController();
        mainController.userInterface();     //МБ СДЕЛАТЬ ТОЖЕ СТАТИЧЕСКИМ МЕТОД
    }
}
