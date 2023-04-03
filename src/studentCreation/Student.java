package studentCreation;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String fullName;
    private String address;
    private Gender gender;
    private int id;
    private LocalDate birthDate;

    private boolean isDeleted = false;

    public Student(int id, String fullName,String address, Gender gender, LocalDate birthDate , boolean isDeleted){
        this.isDeleted = isDeleted;
        this.id = id;
        this.fullName = fullName;
        this. address = address;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Student(int id, String fullName, String address, Gender gender, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;

    }
    public boolean isDeleted() {return isDeleted;}

    public void setDeleted(boolean deleted) {isDeleted = deleted;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Gender getGender() {return gender;}

    public void setGender(Gender gender) {this.gender = gender;}

    public LocalDate getBirthDate() {return birthDate;}

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getMessageDetails() {
        return id + "," + fullName + "," + address + "," + gender + "," + birthDate + "," + isDeleted;
    }

    @Override
    public String toString() {
        return "student management.studentCreation.Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
