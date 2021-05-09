package ua.com.nkrasnovoronka.app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.nkrasnovoronka.lib.Entity;

@Getter
@Setter
@ToString(callSuper = true)
public class Car extends Entity {
    private String manufacturer;
    private String model;
    private String serialNumber;
}
