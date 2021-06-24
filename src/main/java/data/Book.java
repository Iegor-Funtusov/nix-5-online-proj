package data;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    private String id;
    private String title;
    private List<String> authors;
    private boolean visibleFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean isVisibleFlag() {
        return visibleFlag;
    }

    public boolean getVisibleFlag() {
        return visibleFlag;
    }

    public void setVisibleFlag(boolean visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", visibleFlag=" + visibleFlag +
                '}';
    }
}
