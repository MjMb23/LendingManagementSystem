package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;


public class LoanType {
    private String description;
    private int id;
    private float rate;
    private float penaltyRate;
    private String status;

    ResultSet rs;

    public LoanType(int id, String description, float rate, float penaltyRate, String status) {
        this.description=description;
        this.id = id;
        this.rate = rate;
        this.penaltyRate=penaltyRate;
        this.status = status;
    }

    public LoanType(){
        this.id=0;
        this.rate=0;
        this.penaltyRate=0;
        this.status="";
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setPenaltyRate(float penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public float getRate() {
        return rate;
    }

    public float getPenaltyRate() {
        return penaltyRate;
    }

    public String getStatus() {
        return status;
    }

    public int getLoanTypeId(String loanType){

        int id = 0;

        try{
            ConnectionClass getId=new ConnectionClass();
            rs=getId.select(String.format("select loan_type.id from lending_management_system.loan_type where loan_type.description = '%s';",loanType));
            while (rs.next()){
                id= rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("getLoanTypeID method returns null");
            e.printStackTrace();
        }

        return id;
    }

    public String getLoanType(int id){
        String description="";

        try{
            ConnectionClass getId=new ConnectionClass();
            rs=getId.select(String.format("select description from loan_type where id='%d';",id));
            while (rs.next()){
                id= rs.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("getLoanTypeID method returns null");
            e.printStackTrace();
        }

        return  description;
    }
}
