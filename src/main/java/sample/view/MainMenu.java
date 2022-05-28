package sample.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import sample.App;
import sample.controller.GsonHandler;
import sample.model.Game;
import sample.model.User;

import java.io.IOException;
import java.util.ArrayList;


public class MainMenu {
    public ChoiceBox choiceBox;

    public void initialize(){
        handleChoiseBox();
    }

    public void goGame(MouseEvent actionEvent) throws IOException {
        if(choiceBox.getValue() == null){
            Game.getInstance().setHardshipLevel("medium");
        }
        App.setRoot("game");
    }

    public void goProfile(MouseEvent mouseEvent) throws IOException {
        App.setRoot("profile");
    }

    public void goExit(MouseEvent mouseEvent) {

        GsonHandler.exportDataOfUser(User.getAllUsers());
        Platform.exit();
    }

    public void goLoginMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("loginmenu");
    }

    public void goScoreBoard(MouseEvent mouseEvent) throws IOException {
        App.setRoot("scoreboard");
    }

    private void handleChoiseBox(){
        String[] degree = {"easy" , "medium","hard" };
        choiceBox.getItems().addAll(degree);
        choiceBox.setStyle("-fx-font-size: 15");


    }

    public void chooseHardshipDegree(ActionEvent actionEvent) {
        Game.getInstance().setHardshipLevel((String) choiceBox.getValue());
    }

    public void goGuide(MouseEvent mouseEvent) throws IOException {
        App.setRoot("guide");
    }
}
