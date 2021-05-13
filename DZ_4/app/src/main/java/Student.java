public class Student extends BaseEntity {
    private String name;
    private int age;
    private int stud_card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStud_card() {
        return stud_card;
    }

    public void setStud_card(int stud_card) {
        this.stud_card = stud_card;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", stud_card=" + stud_card +
                '}';
    }
}
