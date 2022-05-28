package sample.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.App;
import sample.controller.RegisterController;
import sample.model.Game;
import sample.model.User;
import sample.view.enums.Message;

import java.io.IOException;

public class Profile {
    public ImageView profileImage;
    public Label currentUsername;
    public Label currentPassword;
    public Label score;
    public TextField username;
    public TextField password;
    public Button confirmBtn;
    public ImageView homeBtn;
    public ImageView deleteUser;
    public ImageView logOut;
    public Label error;

    public void initialize(){
        setUsernameAndPasswordAndScore();
    }


    public void checkData(MouseEvent mouseEvent) {
        Message message = RegisterController.changeUsernameOrPassword(username.getText() , password.getText());
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
                error.setText("change successfully");
                error.setStyle("-fx-text-fill: green;");
                RegisterController.changeDataOfUser(username.getText() , password.getText());
                setUsernameAndPasswordAndScore();
                break;
            default:
                break;

        }
        error.setLayoutX(680 - (error.getText().length()*4));
    }

    private void setUsernameAndPasswordAndScore(){
        currentUsername.setText(Game.getInstance().getCurrentUser().getUsername());
        currentPassword.setText(Game.getInstance().getCurrentUser().getPassword());
        score.setText(Integer.toString(Game.getInstance().getCurrentUser().getScore()));
        Image image = new Image(User.class.getResource(Game.getInstance().getCurrentUser().getImage()).toString());
        profileImage.setImage(image);

    }

    public void goLoginMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("loginmenu");
    }
    public void goMainMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("mainmenu");
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        User.deleteAccount(Game.getInstance().getCurrentUser());
        App.setRoot("loginmenu");
    }


}
