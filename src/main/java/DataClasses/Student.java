package DataClasses;

public class Student {
    private String name;
    private Course course;
    private String id;

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if(course == null){
            return "Name: " + name +
                    " id: " + id;
        }
        return "Name: " + name+
                " course: " + course.getNameOfCourse() +
                " id: " + id;
    }
}
