package Controllers;

import Connection.ConnectionClass;
import Models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.net.URL;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LedgerController implements Initializable {

    //NODES
    @FXML
    private ComboBox<?> searchByFilterComboBox;

    @FXML
    private TextField searchForField;

    @FXML
    private ComboBox<?> StatusFilterComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Loan_Account> accountsListTable;

    @FXML
    private TableColumn<Loan_Account, Integer> accountIDColumn;

    @FXML
    private TableColumn<Loan_Account, String> accountNameColumn;

    @FXML
    private TableColumn<Loan_Account, LocalDate> releaseDateColumn;

    @FXML
    private TableColumn<Loan_Account, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<Loan_Account, Double> balanceColumn;

    @FXML
    private TextArea ledgerTextArea;

    //ACTION EVENTS
    @FXML
    void StatusFilterComboBoxChanged(ActionEvent event) {

    }

    @FXML
    void accountsListTableSelected(MouseEvent event) {
        ledgerTextArea.clear();
        makeLedger(getLoanID());

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }

    @FXML
    void searchByFilterComboBoxChanged(ActionEvent event) {

    }

    @FXML
    void searchFieldEntered(ActionEvent event) {

    }

    @FXML
    void searchFieldEnteredTyped(KeyEvent event) {

    }

    //METHODS
    private void populateTable(){
        ResultSet result;
        ObservableList<Loan_Account> accounts=FXCollections.observableArrayList();


        try {
            ConnectionClass getAccountList=new ConnectionClass();
            result=getAccountList.select("SELECT loan_account.id, client.first_name, client.last_name, loan_account.release_date, loan_account.due_date, loan_account.balance\n" +
                    "FROM lending_management_system.loan_account\n" +
                    "JOIN lending_management_system.client\n" +
                    "ON loan_account.Client_id=client.id;");

            while(result.next()){

                Loan_Account accountList=new Loan_Account();
                accountList.setId(result.getInt("id"));
                accountList.setFirstName(result.getString("first_name"));
                accountList.setLastName(result.getString("last_name"));
                accountList.setFullName(accountList.getFirstName()+" "+accountList.getLastName());
                accountList.setReleaseDate(result.getDate("release_date"));
                accountList.setDueDate(result.getDate("due_date"));
                accountList.setBalance(result.getDouble("balance"));
                accounts.add(accountList);
            }
            getAccountList.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        accountIDColumn.setCellValueFactory(new PropertyValueFactory<Loan_Account, Integer>("id"));
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<Loan_Account, String>("fullName"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<Loan_Account, LocalDate>("releaseDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Loan_Account, LocalDate>("dueDate"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Loan_Account, Double>("balance"));

        accountsListTable.setItems(accounts);
    }

    private void makeLedger(int accountID){

        ResultSet rs;
        Loan_Account selectedAccount=new Loan_Account();
        ArrayList<Transaction> transactionsList=new ArrayList<>();
        Transaction transactionToList;

        try{
            ConnectionClass getTransactions=new ConnectionClass();
            rs=getTransactions.select("SELECT payment.date, payment.amount_due, payment.excess, payment.amount_paid, \n" +
                    "payment.balance, payment.penalty, payment.official_reciept_number, payment.status, payment.remarks\n" +
                    "FROM lending_management_system.payment\n" +
                    "JOIN lending_management_system.loan_account\n" +
                    "ON payment.Loan_Account_id=loan_account.id\n " +
                    "WHERE loan_account.id='"+accountID+"' AND payment.status !='pending'" +
                    "ORDER BY payment.id ASC;");
            while (rs.next()){
                transactionToList=new Transaction();
                transactionToList.setScheduledDate(rs.getDate("date").toLocalDate());
                transactionToList.setAmountDue(rs.getDouble("amount_due"));
                transactionToList.setExcess(rs.getDouble("excess"));
                transactionToList.setAmountPaid(rs.getDouble("amount_paid"));
                transactionToList.setBalance(rs.getDouble("balance"));
                transactionToList.setPenalty(rs.getDouble("penalty"));
                transactionToList.setReceiptNumber(rs.getInt("official_reciept_number"));
                transactionToList.setStatus(rs.getString("status"));
                String remarks=rs.getString("remarks");
                if(remarks==null){
                    transactionToList.setRemarks(" ");
                }else{
                    transactionToList.setRemarks(rs.getString("remarks"));
                }


                transactionsList.add(transactionToList);
            }
            getTransactions.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try{
            ConnectionClass getAccountInfo=new ConnectionClass();
            rs=getAccountInfo.select("SELECT client.first_name,  client.last_name, client.address, client.contact_number, loan_account.principal,\n" +
                    "loan_account.due_amount, loan_account.loan_term, loan_account.interest, loan_account.payment_frequency, loan_account.release_date, loan_account.due_date,\n" +
                    "loan_account.scheduled_amount, loan_account.balance\n" +
                    "FROM lending_management_system.loan_account\n" +
                    "JOIN lending_management_system.client\n" +
                    "ON loan_account.Client_id=client.id " +
                    "WHERE loan_account.id ='"+accountID+"';");

            while (rs.next()){



                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MMM. dd, uuuu");
                selectedAccount.setFirstName(rs.getString("first_name"));
                selectedAccount.setLastName(rs.getString("last_name"));
                selectedAccount.setFullName(selectedAccount.getFirstName()+" "+selectedAccount.getLastName());
                selectedAccount.setAddress(rs.getString("address"));
                selectedAccount.setContactNumber(rs.getString("contact_number"));
                selectedAccount.setPrincipal(rs.getDouble("principal"));
                selectedAccount.setAmount(rs.getDouble("due_amount"));
                selectedAccount.setLoanTerm(rs.getInt("loan_term"));
                selectedAccount.setInterest(rs.getDouble("interest"));
                selectedAccount.setPaymentFrequency(rs.getString("payment_frequency"));
                selectedAccount.setReleaseDate(rs.getDate("release_date").toLocalDate());
                selectedAccount.setDueDate(rs.getDate("due_date").toLocalDate());
                selectedAccount.setScheduledAmount(rs.getDouble("scheduled_amount"));
                selectedAccount.setBalance(rs.getDouble("balance"));
            }
            getAccountInfo.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        LocalDate releaseDate=LocalDate.parse(selectedAccount.getReleaseDate().toString());
        LocalDate dueDate=LocalDate.parse(selectedAccount.getDueDate().toString());


        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MMM. dd, uuuu");
        String release = releaseDate.format(formatters);
        String due = dueDate.format(formatters);


        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        String principalAmount=myFormat.format(selectedAccount.getPrincipal());
        String amount=myFormat.format(selectedAccount.getAmount());


        ledgerTextArea.appendText("Name:\t\t\t"+selectedAccount.getFullName());
        ledgerTextArea.appendText("\nAddress:\t\t\t"+selectedAccount.getAddress());
        ledgerTextArea.appendText("\nContact no.:\t\t"+selectedAccount.getContactNumber());
        ledgerTextArea.appendText("\nLoan Amount:\t\t₱"+amount+"\t\t\t\t\tNet proceeds:\t\t₱"+principalAmount);
        ledgerTextArea.appendText("\nLoan term:\t\t"+selectedAccount.getLoanTerm()+" month/s"+"\t\t\t\tInterest:\t\t\t"+selectedAccount.getInterest()*100+"%");
        ledgerTextArea.appendText("\nDate released:\t\t"+release+"\t\t\t\tDue date:\t\t\t"+due);
        ledgerTextArea.appendText("\nAmortization\t\t₱"+selectedAccount.getScheduledAmount()+" "+selectedAccount.getPaymentFrequency());
        ledgerTextArea.appendText("\n\nPayment\t\t\tScheduled\tExcess\t\tTotal\t\tBalance:\t\tPenalty:\t\tStatus:\t\tOR\t\t\tRemarks:");
        ledgerTextArea.appendText("\nDate:\t\t\tPayment:\t\tPayment:\t\tPayment:\t\t\t\t\t\t\t\t\t\t\tnumber:\t\t");

        for(Transaction item : transactionsList) {
            LocalDate dateSchedule=LocalDate.parse(item.getScheduledDate().toString());
            String paymentSchedule=dateSchedule.format(formatters);
            ledgerTextArea.appendText("\n"+paymentSchedule+"\t\t");
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getAmountDue()));
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getExcess()));
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getAmountPaid()));
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getBalance()));
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getPenalty()));
            ledgerTextArea.appendText(String.format("%0$-15s\t",item.getStatus()));
            ledgerTextArea.appendText(String.format("%0$-15s\t\t",item.getReceiptNumber()));
            ledgerTextArea.appendText(String.format("%0$-30s",item.getRemarks()));
        }


    }

    private int getLoanID(){
        int id = 0;
        Loan_Account selectedAccount=accountsListTable.getSelectionModel().getSelectedItem();
        id=selectedAccount.getId();
        return id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTable();

        accountsListTable.getSelectionModel().select(0);
    }
}
