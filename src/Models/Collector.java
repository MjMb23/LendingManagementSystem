package Models;

public class Collector extends Person{
    private int id;
    private int employeeId;

    public Collector(int id, int employeeId, String lastName, String firstName, String middleName, String address, String contactNumber) {
        super(lastName, firstName, middleName, address, contactNumber);
        this.id=id;
        this.employeeId=id;
    }
}
