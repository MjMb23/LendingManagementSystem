package Models;

public class Person {
    private String  lastName, firstName, middleName, address;
    private String contactNumber;
    private String fullName;

    public Person(){
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
        this.address = "";
        this.contactNumber = "";
        this.fullName="";
    }

    public Person(String lastName, String firstName, String middleName, String address, String contactNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.contactNumber = contactNumber;
    }



    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFullName(){
        fullName=this.getFirstName()+" "+this.getLastName();
        return fullName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public static void main(String[] args){
        Person person=new Person();
        person.setFirstName("Mark Jason");
        person.setLastName("Margallo");
        System.out.println(person.getFullName());
    }
}
