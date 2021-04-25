package ua.example.test_package;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestClass {

    public void print(String string){
        System.out.println(string);
    }
}