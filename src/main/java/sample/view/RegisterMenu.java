package sample.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.App;
import sample.controller.RegisterController;
import sample.model.User;
import sample.view.enums.Message;

import java.io.IOException;

public class RegisterMenu {
    public TextField username;
    public TextField password;
    public Button registerBtn;
    public ImageView homeBtn;
    public Label error;


    public void checkUsernameAndPassword(MouseEvent mouseEvent){
        Message message = RegisterController.checkRegisterUsernameAndPassword(username.getText() , password.getText());
        switch (message){
            case EMPTY_FIELD:
                error.setText("Enter your username or password");
                error.setStyle("-fx-text-fill: RED;");
                break;
            case USER_EXIST:
                error.setText("this username is already exist");
                error.setStyle("-fx-text-fill: RED;");
                break;
            case SUCCESS:
                error.setText("register successfully");
                error.setStyle("-fx-text-fill: green;");
                RegisterController.addUser(username.getText() , password.getText());
                break;
            default:
                break;

        }
        error.setLayoutX(350 - (error.getText().length()*4));

    }

    public void goLoginMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("loginmenu");
    }
}
