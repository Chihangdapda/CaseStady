package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;

import javafx.stage.Stage;


public class Controller implements Initializable {
    String user = "Noah";
    String pass = "12";
    @FXML
    public TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void dashBoardView(ActionEvent event) throws IOException {
        String name = username.getText();
        String passw = password.getText();
        if (name.isEmpty() || passw.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Yêu Cầu Nhập Đầy Đủ Các Trường");
            alert.showAndWait();
        } else {
            if (name.equals(user) && passw.equals(pass)) {
                Parent root;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/DashBoard/DashBoardView.fxml")), null);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 737, 512));
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("NHập Thông Tin Không Đúng");
                alert.showAndWait();
            }
        }
    }

    public void cancel(ActionEvent actionEvent) {
        username.setText("");
        password.setText("");
    }
}
