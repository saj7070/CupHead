package sample.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import sample.App;
import sample.controller.GsonHandler;
import sample.controller.LoginController;
import sample.model.Game;
import sample.model.User;
import sample.view.enums.Message;

import java.io.IOException;

public class LoginMenu {
    public TextField username;
    public Button loginBtn;
    public TextField password;
    public Label error;
    public Button registerBtn;
    public Button guestBtn;

    public static void initialize(){
        GsonHandler.importDataOfUser();

    }

    public void goMainMenu(MouseEvent actionEvent) throws IOException {
        Message message = LoginController.checkLoginUsernameAndPassword(username.getText() , password.getText());
        switch (message){
            case EMPTY_FIELD:
                error.setText("Enter your username or password");
                break;
            case USER_NOT_EXIST:
                error.setText("No user exist with this username");
                break;
            case INCORRECT_DATA:
                error.setText("username or password is incorrect");
                break;
            case SUCCESS:
                LoginController.changeCurrentUser(User.getUserByUsername(username.getText()));
                App.setRoot("mainmenu");
                break;
            default:
                break;

        }
        error.setLayoutX(330-error.getText().length()*4.8);

    }

    public void goRegisterMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("registermenu");
    }

    public void loginWithGuestUser(MouseEvent mouseEvent) throws IOException {
        String url = "/assets/images/profile.png";
        User user = new User("Guest" , "nothing"  , url);
        LoginController.changeCurrentUser(user);
        App.setRoot("mainmenu");
    }
}
