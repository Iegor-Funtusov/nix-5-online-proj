package ua.nkrasnovoronka.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AbstractEntity {
    private Long id;
    private boolean visible;

}
