package RestAssuredTest;

public class SpartanPojo {
    private String name;
    private String gender;
    private long phone;


    @Override
    public String toString() {
        return "Spartan{"+

                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
    public SpartanPojo(){}

    public SpartanPojo( String name, String gender, long phone){

        this.name=name;
        this.gender=gender;
        this.phone=phone;

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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
