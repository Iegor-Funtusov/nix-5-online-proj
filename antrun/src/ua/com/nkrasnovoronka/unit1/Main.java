package ua.com.nkrasnovoronka.unit1;

import ua.com.nkrasnovoronka.unit1.data.User;
import ua.com.nkrasnovoronka.unit1.jsoup.PageParser;
import ua.com.nkrasnovoronka.unit1.commons.SystemInformationPrinter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Printing user name");
        User user = new User("Ivan", 23);
        System.out.println(user.getName());
        System.out.println();

        System.out.println("Printing wikipedia page title");
        PageParser pageParser = new PageParser();
        pageParser.printPageTitle("https://en.wikipedia.org/wiki/Main_Page");
        System.out.println();

        System.out.println("Printing java home path");
        SystemInformationPrinter systemInformationPrinter = new SystemInformationPrinter();
        systemInformationPrinter.printJavaHome();
        System.out.println();
    }
}
