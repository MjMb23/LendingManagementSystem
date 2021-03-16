package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;

public class Employee extends Person{
    private int id;
    private String status;
    private String position;

    ResultSet rs;

    public Employee(){
        this.id=0;
        this.status="";
        this.position="";
    }


    public Employee(int id, String lastName, String firstName, String middleName, String address, String contactNumber, String position, String status) {
        super(lastName, firstName, middleName, address, contactNumber);
        this.id=id;
        this.status=status;
        this.position=position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getEmployeeName(int id){
        String fullName="";
        try {
            ConnectionClass getFullName=new ConnectionClass();
            rs=getFullName.select(String.format("SELECT concat(employee.first_name,' ', employee.last_name) as employee_full_name FROM lending_management_system.employee\n" +
                    "where employee.id=%d;",id));
            while(rs.next()){
                fullName=rs.getString("employee_full_name");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  fullName;
    }



    public int getEmployeeID(String fullname){
        int id=0;
        try {
            ConnectionClass getFullName=new ConnectionClass();
            rs=getFullName.select(String.format("SELECT employee.id FROM lending_management_system.employee\n" +
                    "where concat(employee.first_name,' ', employee.last_name)='%s';",fullname));
            while(rs.next()){
                id=rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }
}
