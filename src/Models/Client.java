package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;

public class Client extends Person{
    private int id;
    private String status;
    private int employeeID;
    private String employeeName;

    ResultSet rs;

    public Client(){
        this.id=0;
        this.employeeID=0;
        this.employeeName="";
        this.status="";
    }

    public Client(int id, int employeeID, String lastName, String firstName, String middleName, String address, String contactNumber, String status) {
        super(lastName, firstName, middleName, address, contactNumber);
        this.id=id;
        this.employeeID=employeeID;
        this.status=status;

        try{
            ConnectionClass createAccount=new ConnectionClass();
            createAccount.insert("INSERT INTO lending_management_system.client (`first_name`, `last_name`, `middle_name`, `address`, `contact_number`,  `status`, `Employee_id`)" +
                    " VALUES ('"+this.getFirstName()+"', '"+this.getLastName()+"', '"+this.getMiddleName()+"', '"+this.getAddress()+"', '"+this.getContactNumber()+"', '"+this.getStatus()+"', '"+this.getEmployeeID()+"');");
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    
    public int getCustomerID(String firstname, String lastname, String middlename){
        int id = 0;
        try{
            ConnectionClass getId=new ConnectionClass();
            rs=getId.select(String.format("select client.id from lending_management_system.client where first_name='%s' and last_name ='%s' and middle_name='%s';",firstname, lastname, middlename));
            while(rs.next()){
                id=rs.getInt("id");
            }
            getId.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("getCustomerId() returns null");
            e.printStackTrace();
        }

        return id;
    }

    public static void main(String[] args){

        //Client insertClient= new Client(0,2,"Diaz","Mark Jason", "Margallo", "Olongap City, Zambales", "09197696799", "for approval");
        //int id = insertClient.getCustomerID("Mark Jason", "Diaz", "Margallo");
        //System.out.println(id);

    }
}
