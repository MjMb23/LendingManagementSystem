package Models;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class dueDateTrial {

    public static void main(String[] args){
        LocalDate now=LocalDate.parse("2021-02-06");
        //LocalDate dueDate=LocalDate.now().plusMonths(4);

        int monthsCount=4;
        int present=0;

        while(present<=26*monthsCount+1){

            if(!(now.getDayOfWeek()==DayOfWeek.SUNDAY)){
                present++;
                System.out.println(now);
            }
            now=now.plusDays(1);
        }
        //System.out.println(now);
    }
}
