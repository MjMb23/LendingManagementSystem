package Models;

import Connection.ConnectionClass;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calculation {
    private boolean release;
    private int userID, collectorID, loanID;
    private double loanAmount;
    private String loanType;
    private String paymentFrequency;
    private double interestRate;
    private int loanTermValue;
    private String loanTermMeasure;
    private double paymentPerFrequency;
    private double totalAmount;
    private boolean includeSundays;
    private LocalDate dueDate;
    private LocalDate nextSchedule;

    private LocalDate today=LocalDate.now();

    public Calculation(){

    }

    public Calculation(double loanAmount, String loanType, String paymentFrequency, boolean includeSundays, double interestRate, int loanTermValue, String loanTermMeasure) {
        this.userID=0;
        this.collectorID=0;
        this.loanID=0;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.paymentFrequency = paymentFrequency;
        this.includeSundays=includeSundays;
        this.interestRate = interestRate;
        this.loanTermValue = loanTermValue;
        this.loanTermMeasure = loanTermMeasure;
        this.dueDate=LocalDate.now();
        this.nextSchedule=LocalDate.now();

        LocalDate now = LocalDate.now();

        int interestMultiplier = 0;

        if(loanTermMeasure=="Month/s"){
            interestMultiplier=1*loanTermValue;
        }

        int divisor = 0;

        if(this.paymentFrequency.equals("Daily")){

            if(this.includeSundays==true){
                this.nextSchedule=now.plusDays(1);
                divisor=30*this.loanTermValue;

                int dayCount=0;

                while(dayCount<30*this.loanTermValue){
                        now=now.plusDays(1);
                        dayCount++;
                }
                this.dueDate=now;
            }

            else if(this.includeSundays==false){
                if(now.plusDays(1).getDayOfWeek()==DayOfWeek.SUNDAY){
                    this.nextSchedule=now.plusDays(2);
                }else{
                    this.nextSchedule=now.plusDays(1);
                }
                
                divisor=26*this.loanTermValue;
                int days=0;

                while(days<26*this.loanTermValue){
                    now=now.plusDays(1);
                    if(!(now.getDayOfWeek()==DayOfWeek.SUNDAY)){
                        days++;
                    }
                }
                this.dueDate=now;
            }
        }
        else if(this.paymentFrequency.equals("Weekly")){
            divisor=4*this.loanTermValue;

            if(now.plusWeeks(1).getDayOfWeek()==DayOfWeek.SUNDAY){
                this.nextSchedule=now.plusWeeks(1).plusDays(1);
            }else{
                this.nextSchedule=now.plusWeeks(1);
            }

            int weekCount=0;

            while(weekCount<divisor){
                now=now.plusWeeks(1);
                weekCount++;
            }
            this.dueDate=now;
        }
        else if(this.paymentFrequency.equals("Fortnightly")){
            divisor=2*this.loanTermValue;

            this.nextSchedule=now.plusWeeks(2);

            int fortnightCount=0;

            while(fortnightCount<divisor){
                now=now.plusWeeks(2);
                fortnightCount++;
            }
            this.dueDate=now;
        }
        else if(this.paymentFrequency.equals("Monthly")){
            divisor=1*this.loanTermValue;

            this.nextSchedule=now.plusMonths(1);

            int monthCount=0;
            while(monthCount<divisor){
                now=now.plusMonths(1);
                monthCount++;
            }
            this.dueDate=now;
        }

        LocalDate dateToday=LocalDate.now();

        double tempTotal;
        double tempAmort;
        double weeklyAdditional=0;
        double dailyCharge=0;

        if(this.getPaymentFrequency().equals("Weekly")){

            int dailyCount=26*this.getLoanTermValue();

            tempTotal=this.totalAmount=Math.round(this.loanAmount+((this.loanAmount*interestRate)*interestMultiplier));
            tempAmort=tempTotal/dailyCount;

            dailyCharge=tempAmort*.025;
            System.out.println("daily charge: "+dailyCharge+" daily count:"+dailyCount+" temp total: "+tempTotal+" temp amort:"+tempAmort);

            weeklyAdditional=0;
            int index=1 ;

            while(index<=dailyCount){

                System.out.println("index: "+index+" weekly additional "+weeklyAdditional+" daily charge "+dailyCharge);
                weeklyAdditional+=dailyCharge;
                index++;

            }

            this.totalAmount=Math.round((this.loanAmount+((this.loanAmount*interestRate)*interestMultiplier)+weeklyAdditional));

        }else{
            this.totalAmount=Math.round(this.loanAmount+((this.loanAmount*interestRate)*interestMultiplier));
        }

        //this.totalAmount=Math.round(this.loanAmount+((this.loanAmount*interestRate)*interestMultiplier));
        this.paymentPerFrequency=Math.ceil(this.getTotalAmount()/divisor);

    }

    public void insertTransactions(){

        try {
            ConnectionClass insertTransaction = new ConnectionClass();
            insertTransaction.insert("INSERT INTO lending_management_system.payment (User_Account_id, date, amount_due, amount_paid, excess, penalty, payment_date, status, Collector_id, Loan_Account_id) " +
                    "VALUES ('"+this.getUserID()+"', '"+this.getNextSchedule()+"', '"+this.getPaymentPerFrequency()+"', '0', '0', '0', '0000-00-00', 'ongoing', '"+this.collectorID+"', '"+this.loanID+"');\n");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public int getUserID() {
        return userID;
    }

    public int getCollectorID() {
        return collectorID;
    }

    public int getLoanID() {
        return loanID;
    }

    public LocalDate getNextSchedule() {
        return nextSchedule;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getLoanTermValue() {
        return loanTermValue;
    }

    public String getLoanTermMeasure() {
        return loanTermMeasure;
    }

    public double getPaymentPerFrequency() {
        return paymentPerFrequency;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCollectorID(int collectorID) {
        this.collectorID = collectorID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setLoanTermValue(int loanTermValue) {
        this.loanTermValue = loanTermValue;
    }

    public void setLoanTermMeasure(String loanTermMeasure) {
        this.loanTermMeasure = loanTermMeasure;
    }

    public void setPaymentPerFrequency(double paymentPerFrequency) {
        this.paymentPerFrequency = paymentPerFrequency;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setIncludeSundays(boolean includeSundays) {
        this.includeSundays = includeSundays;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setNextSchedule(LocalDate nextSchedule) {
        this.nextSchedule = nextSchedule;
    }

    public boolean isIncludeSundays() {
        return includeSundays;
    }

    public static void main(String[] args){

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        Calculation sample=new Calculation(20000,"lending","Daily",false,.05,4,"Month/s");
        System.out.println(sample.getDueDate());
        System.out.println("Total:\t\t\t"+myFormat.format(sample.getTotalAmount()));
        System.out.println("Payment:\t\t"+myFormat.format(sample.getPaymentPerFrequency()));
        System.out.println("Next Schedule:\t"+sample.getNextSchedule());
    }
}
