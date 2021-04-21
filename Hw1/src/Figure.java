import java.util.UUID;

public abstract class Figure{
    private String id;

    Figure(){
        this.id = UUID.randomUUID().toString();
    }

    public abstract double calcPerimeter();
    public abstract double calcArea();

    public String getId(){
        return this.id;
    }
}