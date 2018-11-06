package my.app.entities;

import java.util.Date;

public class Detail {
    private int id;
    private String motto;
    private Date birthday;
    private int age;
    private String star;
    private String email;
    private String name;
    private String gender;
    private String conpany;
    private String school;
    private String position;
    private String profession;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConpany() {
        return conpany;
    }

    public void setConpany(String conpany) {
        this.conpany = conpany;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", motto='" + motto + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", star='" + star + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", conpany='" + conpany + '\'' +
                ", school='" + school + '\'' +
                ", position='" + position + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
