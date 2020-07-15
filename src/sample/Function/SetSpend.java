package sample.Function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SetSpend {
    public static int total;

    @FXML
    private TextField proceeds;

    public void accept(ActionEvent event) {
         String a = proceeds.getText();
        total = total+ Integer.valueOf(a);

    }

    public void cancel(ActionEvent event) {
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
