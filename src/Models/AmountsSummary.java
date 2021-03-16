package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;
import java.time.LocalDate;

public class AmountsSummary {
    ResultSet rs;
    LocalDate localDate;

    private double todayAmount=0;
    private double yesterdayAmount=0;
    private double thisWeekAmount=0;
    private double thisMonthAmount=0;
    private double thisYearAmount=0;

    public double getTodayAmount(String tableName, String field, String dateFieldName) {
        double total=0;
        localDate=LocalDate.now();
        try{
            ConnectionClass conn=new ConnectionClass();
            rs = conn.select(String.format("SELECT %s FROM lending_management_system.%s WHERE %s = '%s'",field, tableName,dateFieldName,localDate));
            while (rs.next()){
                todayAmount=rs.getDouble(field);
                total+=todayAmount;
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public double getYesterdayAmount(String tableName, String field, String dateFieldName) {
        double total=0;
        localDate=LocalDate.now().minusDays(1);
        try{
            ConnectionClass conn=new ConnectionClass();
            rs = conn.select(String.format("SELECT %s FROM lending_management_system.%s WHERE %s = '%s'",field, tableName, dateFieldName, localDate));
            while (rs.next()){
                yesterdayAmount=rs.getDouble(field);
                total+=yesterdayAmount;
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public double getThisWeekAmount(String tableName, String field, String dateFieldName) {
        double total=0;
        localDate=LocalDate.now();
        try{
            ConnectionClass conn=new ConnectionClass();
            rs = conn.select(String.format("SELECT %s FROM lending_management_system.%s WHERE  WEEK(`%s`, 1) = WEEK(CURRENT_DATE(), 1);",field, tableName, dateFieldName));
            while (rs.next()){
                thisWeekAmount=rs.getDouble(field);
                total+=thisWeekAmount;
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public double getThisMonthAmount(String tableName, String field, String dateFieldName) {
        double total=0;
        localDate=LocalDate.now();
        try{
            ConnectionClass conn=new ConnectionClass();
            rs = conn.select(String.format("SELECT %s FROM lending_management_system.%s WHERE YEAR(%s) = YEAR(CURRENT_DATE()) AND MONTH(%s) = MONTH(CURRENT_DATE());",field, tableName, dateFieldName, dateFieldName));
            while (rs.next()){
                thisMonthAmount=rs.getDouble(field);
                total+=thisMonthAmount;
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public double getThisYearAmount(String tableName, String field, String dateFieldName) {
        double total=0;
        localDate=LocalDate.now();
        try{
            ConnectionClass conn=new ConnectionClass();
            rs = conn.select(String.format("SELECT %s FROM lending_management_system.%s WHERE YEAR(%s) = YEAR(CURRENT_DATE())",field, tableName, dateFieldName));
            while (rs.next()){
                thisYearAmount=rs.getDouble(field);
                total+=thisYearAmount;
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public static void main(String[] args){

        AmountsSummary sample=new AmountsSummary();
        sample.getThisWeekAmount("payment", "amount_paid", "payment_date");

    }
}
