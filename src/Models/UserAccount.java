package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;

public class UserAccount extends Person{

    private int id;
    private int employeeId;
    private String username;
    private String password;
    private String status;
    private boolean admin;

    ResultSet rs;

    public UserAccount(){
        this.id=0;
        this.employeeId=0;
        this.username="";
        this.password="";
        this.status="";
        this.admin=true;
    }

    public int getId() {
        return id;
    }

    public UserAccount(int id, int employeeId, String lastName, String firstName, String middleName, String address, String contactNumber, String username, String password, String status, boolean admin) {
        super(lastName, firstName, middleName, address, contactNumber);
        this.id=id;
        this.employeeId=employeeId;
        this.username=username;
        this.password=password;
        this.status=status;
        this.admin=admin;
    }

    public int getUserId(String username){
        int id=0;
        try {
            ConnectionClass getFullName=new ConnectionClass();
            rs=getFullName.select(String.format("SELECT user_account.id FROM lending_management_system.user_account \n" +
                    "where username='%s';",username));

            while(rs.next()){
                id=rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName(int id){

        String userName = "";
        try {
            ConnectionClass getFullName=new ConnectionClass();
            rs=getFullName.select(String.format("SELECT user_account.username FROM lending_management_system.user_account \n" +
                    "where id='%d';",id));

            while(rs.next()){
                username=rs.getString("username");
            }
            getFullName.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  username;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
