public class Patient extends BaseEntity {

    private String name;
    private Integer age;
    private Integer insuranceNumber;

    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Patient setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getInsuranceNumber() {
        return insuranceNumber;
    }

    public Patient setInsuranceNumber(Integer insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", insurance Number='" + insuranceNumber + '\'' +
                '}';
    }
}
