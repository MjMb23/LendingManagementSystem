package Models;

import java.time.LocalDate;

public class Transaction {

    private int id;
    private int loanId;
    private double balance;
    private String accountName;
    private int userAccountId;
    private int collectorId;
    private String assignedEmployee;
    private LocalDate scheduledDate;
    private LocalDate paymentDate;
    private double amountDue;
    private double amountPaid;
    private double penalty;
    private double excess;
    private String status;
    private String remarks;
    private int receiptNumber;

    public Transaction(){

    }

    public Transaction(int id, int loanId, String accountName,  int userAccountId, int collectorId, LocalDate scheduledDate, LocalDate paymentDate, double amountPaid, double amountDue, double penalty,
                       double excess, int receiptNumber, String status) {

        this.id = id;
        this.accountName=accountName;
        this.loanId = loanId;
        this.userAccountId = userAccountId;
        this.collectorId = collectorId;
        this.scheduledDate = scheduledDate;
        this.paymentDate=paymentDate;
        this.amountPaid = amountPaid;
        this.amountDue = amountDue;
        this.penalty = penalty;
        this.excess = excess;
        this.receiptNumber = receiptNumber;
        this.status = status;

    }

    public Transaction(int id, int loanId, String accountName, String assignedEmployee, LocalDate scheduledDate, double amountDue,  double penalty) {

        this.id=id;
        this.accountName=accountName;
        this.loanId = loanId;
        this.assignedEmployee=assignedEmployee;
        this.scheduledDate = scheduledDate;
        this.amountDue = amountDue;
        this.penalty = penalty;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public void setExcess(double excess) {
        this.excess = excess;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public int getId() {
        return id;
    }

    public int getLoanId() {
        return loanId;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getPenalty() {
        return penalty;
    }

    public double getExcess() {
        return excess;
    }

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }
}




















































