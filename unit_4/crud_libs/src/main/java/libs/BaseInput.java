package libs;

public class BaseInput {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseInput{" +
                "id='" + id + '\'' +
                '}';
    }
}
