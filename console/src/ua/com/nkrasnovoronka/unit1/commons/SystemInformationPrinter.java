package ua.com.nkrasnovoronka.unit1.commons;

import org.apache.commons.lang3.SystemUtils;

public class SystemInformationPrinter {

    public void printJavaHome(){
        System.out.println(SystemUtils.getJavaHome());
    }

}
