package Services;

import libs.BaseInput;

public class Student extends BaseInput {
    private String name;
    private int course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + super.getId() + '\'' +
                " name='" + name + '\'' +
                ", course=" + course +
                '}';
    }
}
