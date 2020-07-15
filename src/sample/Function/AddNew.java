package sample.Function;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DashBoard.DashBoardController;
import sample.FileManager.FileManager;
import sample.Spend;
import sample.SpendManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddNew implements Initializable {
    @FXML
    private TextField nameObj;
    @FXML
    private TextField contentObj;

    @FXML
    private TextField moneyObj;

    @FXML
    private TextField noteObj;

    @FXML
    private DatePicker dateObj;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateObj.setValue(LocalDate.now());

    }

    public void okObj(ActionEvent event) throws IOException {
        Spend spend = new Spend();
        spend.setName(nameObj.getText());
        spend.setContent(contentObj.getText());
        spend.setMoney(Integer.parseInt(moneyObj.getText()));
        spend.setNote(noteObj.getText());
        spend.setDate(dateObj.getValue());
        List<Spend> spendList = FileManager.readFile();
//        List<Spend> spendList = SpendManager.getSpendList();
        spendList.add(spend);
        FileManager.writeFile(spendList);
    }

    public void backObj(ActionEvent event) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/DashBoard/DashBoardView.fxml")), null);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 737, 512));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("them moi");
        }
    }
}
