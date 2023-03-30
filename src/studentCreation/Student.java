package studentCreation;

public class Student {
    private String name;
    private String address;
    private String gender;
    private int date;
    private int id;

    private boolean isDeleted = false;

    public Student(int id, String name,String address, String gender, int date, boolean isDeleted){
        this.isDeleted = isDeleted;
        this.id = id;
        this.name = name;
        this. address = address;
        this.gender = gender;
        this.date = date;
    }

    public Student(int id, String name, int parseInt, double parseDouble) {
    }

    public Student(int parseInt, String studentStringSplit, int parseInt1, double parseDouble, boolean parseBoolean) {
    }

    public boolean isDeleted() {return isDeleted;}

    public void setDeleted(boolean deleted) {isDeleted = deleted;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public int getDate() {return date;}

    public void setDate(int date) {this.date = date;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getMessageDetails() {
        return id + "," + name + "," + address + "," + gender + "," + date + "," + isDeleted;
    }

    @Override
    public String toString() {
        return "student management.studentCreation.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", gender=" + gender +
                ",date=" + date +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
