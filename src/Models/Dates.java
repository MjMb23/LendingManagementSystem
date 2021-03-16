package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dates {
    private String dateToday;
    private String dateYesterday;
    private String thisWeek;
    private String thisMonth;
    private String thisYear;
    String dateNextWeek;



    public String getDateToday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");
        dateToday=sdf.format(calendar.getTime());
        return dateToday;
    }

    public String getDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");
        calendar.add(Calendar.DATE, -1);
        dateYesterday=sdf.format(calendar.getTime());
        return dateYesterday;
    }

    public String getThisWeek() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat today = new SimpleDateFormat("dd");
        dateToday=today.format(calendar.getFirstDayOfWeek());
        SimpleDateFormat nextWeek = new SimpleDateFormat("dd");
        calendar.add(Calendar.DATE,7);
        dateNextWeek=nextWeek.format(calendar.getFirstDayOfWeek()+7);
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        thisMonth=month.format(calendar.getTime());
        SimpleDateFormat year= new SimpleDateFormat("yyyy");
        thisYear=year.format(calendar.getTime());
        thisWeek=String.format("%s. %s-%s, %s",thisMonth, dateToday, dateNextWeek, thisYear);
        return thisWeek;
    }

    public String getThisMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMMMMM, yyyy");
        thisMonth=sdf.format(calendar.getTime());
        return thisMonth;
    }

    public String getThisYear() {
        return thisYear;
    }

    public static void main(String[] args){
        Dates date= new Dates();
        System.out.println(date.getDateToday());
        System.out.println(date.getDateYesterday());
        System.out.println(date.getThisWeek());
        System.out.println(date.getThisMonth());
        System.out.println(date.getThisYear());



    }
}
