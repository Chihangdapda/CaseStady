package sample.Function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Spend;
import sample.SpendManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportSpend implements Initializable {
    @FXML
    private TableView<Spend> tableReport;

    @FXML
    private TableColumn<Spend, String> nameReport;
    @FXML
    private TableColumn<Spend, String> ContentReport;
    @FXML
    private TableColumn<Spend, Integer> moneyReport;
    @FXML
    private TableColumn<Spend, LocalDate> dateReport;
    @FXML
    private TableColumn<Spend, String> noteReport;
    private List<Spend> spendList = SpendManager.init();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spendList = SpendManager.getSpendList();
        nameReport.setCellValueFactory(new PropertyValueFactory<Spend, String>("name"));
        ContentReport.setCellValueFactory(new PropertyValueFactory<Spend, String>("content"));
        moneyReport.setCellValueFactory(new PropertyValueFactory<Spend, Integer>("money"));
        dateReport.setCellValueFactory(new PropertyValueFactory<Spend, LocalDate>("date"));
        noteReport.setCellValueFactory(new PropertyValueFactory<Spend, String>("note"));
        tableReport.getItems().clear();
        tableReport.getItems().addAll(spendList);
    }

    public void backDashBoard(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/DashBoard/DashBoardView.fxml")), null);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 737, 512));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {           e.printStackTrace();
            System.out.println("them moi");
        }
    }
}
