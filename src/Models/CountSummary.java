package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;

public class CountSummary {
    private int forApprovalCount;
    private int approvedCount;
    private int activeCount;
    private int overDueCount;
    private int settledCount;
    private int blockedCount;

    public int getForApprovalCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='for approval'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        forApprovalCount=count;
        return forApprovalCount;
    }

    public int getApprovedCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='approved'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        approvedCount=count;
        return approvedCount;
    }

    public int getActiveCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='active'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        activeCount=count;
        return activeCount;
    }

    public int getOverDueCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='overdue'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        overDueCount=count;
        return overDueCount;
    }

    public int getSettledCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='settled'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        settledCount=count;
        return settledCount;
    }

    public int getBlockedCount() {
        int count=0;
        ResultSet resultSet;
        try {
            ConnectionClass sample=new ConnectionClass();
            resultSet=sample.select("SELECT * FROM loan_account WHERE status='blocked'");

            while(resultSet.next()){
                ++count;
            }
            sample.close();
        }catch (Exception e){
            count=0;
            System.out.println(e.getMessage());
        }
        blockedCount=count;
        return blockedCount;
    }

    public static void main(String[] args){



    }
}
