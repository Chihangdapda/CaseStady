package sample.DashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import sample.FileManager.FileManager;
import sample.Function.SetSpend;
import sample.Spend;
import sample.SpendManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private DatePicker dateSearch;
    @FXML
    private TextField textSearch;
    @FXML
    private Label totalMoney;
    @FXML
    private Label moneySpent;
    @FXML
    private Label moneyUnSpent;
    @FXML
    private TableView<Spend> table;

    @FXML
    private TableColumn<Spend, String> nameColumn;
    @FXML
    private TableColumn<Spend, String> contentColumn;
    @FXML
    private TableColumn<Spend, Integer> moneyColumn;
    @FXML
    private TableColumn<Spend, LocalDate> dateColumn;
    @FXML
    private TableColumn<Spend, String> noteColumn;

//    @FXML
//    private TableColumn <Spend, Integer> moneyColumn1;
//    moneyColumn1.setCellFactory;


    private List<Spend> spendList = SpendManager.init();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        display();

        textSearch.textProperty().addListener((observableValue, s, t1) -> {
            search();
        });
        dateSearch.valueProperty().addListener((observableValue, date, t1) -> {
            search();
        });
    }


    public void add(ActionEvent event) throws IOException {
        Parent root;
        try {
            ResourceBundle resources = null;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/Function/AddView.fxml")), resources);
            Stage stage = new Stage();
            stage.setTitle("Thêm chi tiêu");
            stage.setScene(new Scene(root, 737, 512));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void display() {
        spendList = SpendManager.getSpendList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Spend, String>("name"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<Spend, String>("content"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<Spend, Integer>("money"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Spend, LocalDate>("date"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Spend, String>("note"));
        table.getItems().clear();
        table.getItems().addAll(spendList);
        table.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        contentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        moneyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        noteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalMoney.setText(String.valueOf(SetSpend.total));
        moneySpent.setText(String.valueOf(calculateSpend()));
        moneyUnSpent.setText(String.valueOf(SetSpend.total-calculateSpend()));
    }

    public void delete(ActionEvent event) throws IOException {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        Spend spend = new Spend();
        List<Spend> spendList = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            spend = table.getItems().get(i);
            spendList.add(spend);
        }
        FileManager.writeFile(spendList);
    }

    public void search() {
        String searchKey = textSearch.getText();
        LocalDate date = dateSearch.getValue();
        table.getItems().clear();
        for (Spend s : FileManager.readFile()) {
            boolean isMatchName = s.getName().toLowerCase().contains(searchKey.toLowerCase());
            boolean isMatchContent = s.getContent().toLowerCase().contains(searchKey.toLowerCase());
            if ((isMatchName || isMatchContent) && isSameDate(s.getDate(), date)) {
                table.getItems().add(s);
            }
        }
    }

    public boolean isSameDate(LocalDate date1, LocalDate date2) {
        if (date2 == null) return true;
        return (date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() &&
                date1.getDayOfMonth() == date2.getDayOfMonth());
    }

    public void editMoney(TableColumn.CellEditEvent<Spend, Integer> spendStringCellEditEvent) throws IOException {
        Spend spend = table.getSelectionModel().getSelectedItem();
        Integer s = spendStringCellEditEvent.getNewValue();
        System.out.println(s);
        spend.setMoney(s);
        Spend spends;
        List<Spend> spendList = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            spends = table.getItems().get(i);
            spendList.add(spends);
        }
        FileManager.writeFile(spendList);
        display();
    }

    public void editName(TableColumn.CellEditEvent<Spend, String> spendStringCellEditEvent) throws IOException {
        Spend spend = table.getSelectionModel().getSelectedItem();
        spend.setName(spendStringCellEditEvent.getNewValue());
        Spend spends;
        List<Spend> spendList = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            spends = table.getItems().get(i);
            spendList.add(spends);
        }
        FileManager.writeFile(spendList);
    }

    public void editDate(TableColumn.CellEditEvent<Spend, LocalDate> spendStringCellEditEvent) throws IOException {
        Spend spend = table.getSelectionModel().getSelectedItem();
        spend.setDate(spendStringCellEditEvent.getNewValue());
        Spend spends;
        List<Spend> spendList = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            spends = table.getItems().get(i);
            spendList.add(spends);
        }
        FileManager.writeFile(spendList);
    }


    public void report(ActionEvent event) throws IOException {
        Parent root;
        try {
            ResourceBundle resources = null;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/Function/ReportSpend.fxml")), resources);
            Stage stage = new Stage();
            stage.setTitle("Thêm chi tiêu");
            stage.setScene(new Scene(root, 737, 512));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logOut(ActionEvent event) throws IOException {
        Parent root;
        try {
            ResourceBundle resources = null;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample\\sample.fxml")), resources);
            Stage stage = new Stage();
            stage.setTitle("Thêm chi tiêu");
            stage.setScene(new Scene(root, 737, 512));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setTotalRemaining(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Function/SetSpend.fxml"));
        Parent dashboardView = loader.load();
        Scene scene = new Scene(dashboardView);
        stage.setScene(scene);

    }

    public Integer calculateSpend(){
        int sum = 0;
        List<Spend> spendList = FileManager.readFile();
        for (int i=0;i<spendList.size();i++){
           sum = sum+ spendList.get(i).getMoney();
        }
      return sum;
    }
}

