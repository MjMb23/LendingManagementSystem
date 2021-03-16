package Models;

import Connection.ConnectionClass;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Loan_Account extends Person{
    private int id;
    private int loanTypeId;
    private int employeeId;
    private String loanDescription;
    private int clientId;
    private double principal;
    private double interest;
    private Object releaseDate;
    private Object dueDate;
    private int loanTerm;
    private double penalty;
    private double balance;
    private double amount;
    private String status;
    private String paymentFrequency;
    private Object nextSchedule;
    private double scheduledAmount;
    private boolean skipSundays;
    private String assignedEmployee;
    private String receiverUser;
    private int userId;
    private boolean release;

    ResultSet rs;

    public Loan_Account(){
        super();
        this.id=0;
        this.loanDescription="";
        this.principal=0;
        this.interest=0;
        this.interest=0;
        this.loanTerm=0;
        this.amount=0;
        this.penalty=0;
        this.releaseDate=LocalDate.now();
        this.dueDate=LocalDate.now();
        this.scheduledAmount=0;
        this.nextSchedule=LocalDate.now();
        this.status="";
        this.assignedEmployee="";
        this.receiverUser="";
        this.release=false;

    }

    public Loan_Account(boolean release, int id, int loanTypeId, int employeeId, int clientId, String lastName, String firstName, String middleName, String address, String contactNumber,
                        String loanDescription, double principal, double interest, Object releaseDate, String paymentFrequency, boolean skipSundays, Object dueDate, int loanTerm,
                        double balance, double amount, double penalty, String status, Object nextSchedule, double scheduledAmount, String assignedEmployee, String receiverUser, int userId) {

        super(lastName, firstName, middleName, address, contactNumber);
        this.release=release;
        this.id=id;
        this.loanTypeId=loanTypeId;
        this.loanDescription=loanDescription;
        this.clientId=clientId;
        this.employeeId=employeeId;
        this.principal=principal;
        this.interest=interest;
        this.releaseDate=releaseDate;
        this.dueDate=dueDate;
        this.paymentFrequency=paymentFrequency;
        this.loanTerm=loanTerm;
        this.penalty=penalty;
        this.balance=balance;
        this.status=status;
        this.nextSchedule=nextSchedule;
        this.scheduledAmount=scheduledAmount;
        this.amount=amount;
        this.skipSundays=skipSundays;
        this.assignedEmployee=assignedEmployee;
        this.receiverUser=receiverUser;
        this.userId=userId;
        int value = this.skipSundays ? 1 : 0;

        if(this.release==false){
            try{
                ConnectionClass createAccount=new ConnectionClass();
                createAccount.insert("INSERT INTO lending_management_system.loan_account (`principal`, `interest`, `loan_term`, `payment_frequency`, `skip_sundays`, `due_amount`,  `penalty`, `balance`, `status`, `scheduled_amount`, `Loan_Type_id`, `Client_id`, `User_account_id`)" +
                        " VALUES ('"+this.principal+"', '"+this.interest+"', '"+this.loanTerm+"', '"+this.paymentFrequency+"', '"+value+"', '"+this.amount+"', '"+this.penalty+"', '"+this.balance+"', '"+this.status+"', '"+this.scheduledAmount+"', '"+this.loanTypeId+"', '"+this.clientId+"', '"+this.userId+"');");
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Loan_Account Constructor release");
                e.printStackTrace();
            }
        }else{
            try{
                ConnectionClass createAccount=new ConnectionClass();
                createAccount.insert("INSERT INTO lending_management_system.loan_account (`principal`, `interest`, `release_date`, `loan_term`, `payment_frequency`, `skip_sundays`, `due_amount`, `due_date`,  `penalty`, `balance`, `status`, `next_schedule`, `scheduled_amount`, `Loan_Type_id`, `Client_id`, `User_account_id`)" +
                        " VALUES ('"+this.principal+"', '"+this.interest+"','"+this.releaseDate+"', '"+this.loanTerm+"', '"+this.paymentFrequency+"', '"+value+"', '"+this.amount+"', '"+this.dueDate+"', '"+this.penalty+"', '"+this.balance+"', '"+this.status+"', '"+this.nextSchedule+"', '"+this.scheduledAmount+"', '"+this.loanTypeId+"', '"+this.clientId+"', '"+this.userId+"');");
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Loan_Account Constructor not release");
                e.printStackTrace();
            }
        }
    }

    public void setRelease(boolean release) {
        this.release = release;
    }



    public boolean isRelease() {
        return release;
    }

    public int getId() {
        return id;
    }

    public void setId(int loanAccountId) {
        this.id = loanAccountId;
    }

    public int getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Object getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Object releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Object getDueDate() {
        return dueDate;
    }

    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public Object getNextSchedule() {
        return nextSchedule;
    }

    public void setNextSchedule(Object nextSchedule) {
        this.nextSchedule = nextSchedule;
    }

    public double getScheduledAmount() {
        return scheduledAmount;
    }

    public void setScheduledAmount(double scheduledAmount) {
        this.scheduledAmount = scheduledAmount;
    }

    public boolean isSkipSundays() {
        return skipSundays;
    }

    public void setSkipSundays(boolean skipSundays) {
        this.skipSundays = skipSundays;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser;
    }

    public int getLoanTypeID(String loanType){
        int id;
        try{
            ConnectionClass getID=new ConnectionClass();
            getID.select(String.format("SELECT id FROM lending_management_system.loan_type WHERE description = '%s';",loanType));
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public String getUserName(int id){
        String userFullName = null;
        try{
            ConnectionClass getUser=new ConnectionClass();
            rs=getUser.select(String.format("SELECT CONCAT( employee.first_name,' ', employee.last_name) as user_fullname\n" +
                    "FROM lending_management_system.employee\n" +
                    "JOIN lending_management_system.user_account\n" +
                    "ON user_account.Employee_id=employee.id  \n" +
                    "WHERE user_account.Employee_id = %d;",id));
            while(rs.next()){
                userFullName=rs.getString("user_fullname");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return userFullName;
    }

    public static void main(String[] args){
        LocalDate date=LocalDate.now();
        Loan_Account sample=new Loan_Account(false, 0,1,1,1,"Mark","Mark","Diaz","Pampanga","09567132120", "Lending",100,.05, date, "Daily",true, date, 1, 105, 100.00,0,"for approval",date,20, " ", " ",1);
    }
}
