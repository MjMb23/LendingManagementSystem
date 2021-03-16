package Models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class daysOfTheWeek {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        Calendar calendar = Calendar.getInstance();

        LocalDate monday = today.with(previousOrSame(MONDAY));
        LocalDate sunday = today.with(nextOrSame(SUNDAY));

        SimpleDateFormat sdf = new SimpleDateFormat("MMM. DD, YYYY");
        //monday=sdf.format(today.getDayOfWeek());

        System.out.println("Today: " + today);
        System.out.println("Monday of the Week: " + monday);
        System.out.println("Sunday of the Week: " + sunday);
    }
}