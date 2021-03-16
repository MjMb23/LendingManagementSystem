package Models;

import Connection.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Due {
    private String clientName;
    private String collector;

    ConnectionClass connect;
    ResultSet rs;
    ObservableList<Due> dueTodays = FXCollections.observableArrayList();

    public Due(String clientName, String collector) {
        this.clientName = clientName;
        this.collector = collector;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCollector() {
        return collector;
    }

    public static void main(String[] args){
        System.out.println();
    }
}
