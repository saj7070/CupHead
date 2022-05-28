package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sample.App;
import sample.model.Game;
import sample.model.User;

import java.io.IOException;
import java.util.Comparator;

public class ScoreBoard {
    public AnchorPane board;


    public void initialize(){
        sortUserByScore();
        showScoreBoard();
    }

    private void showScoreBoard() {
        int i = 1;
        for (User user: User.getAllUsers()) {
            if(i < 7){
                addUserToScoreBoard(user , i);
                i++;
            }

        }
    }

    private void addUserToScoreBoard(User user , int i){

        Label username = new Label();
        username.setText(user.getUsername());
        username.setLayoutX(200);
        username.setLayoutY(70+(i*75));
        username.setStyle("-fx-font-size: 30 ; -fx-font-family: 'Arial Rounded MT Bold'");

        Label rank = new Label();
        rank.setText(Integer.toString(i));
        rank.setLayoutX(50);
        rank.setLayoutY(70+(i*75));
        rank.setStyle("-fx-font-size: 30 ; -fx-font-family: 'Arial Rounded MT Bold'");

        Label score = new Label();
        score.setText(Integer.toString(user.getScore()));
        score.setLayoutX(380 - (double)((double)score.getText().length()*16)/2);
        score.setLayoutY(70+(i*75));
        score.setStyle("-fx-font-size: 30 ; -fx-font-family: 'Arial Rounded MT Bold'");

        Image image = new Image(String.valueOf(getClass().getResource(user.getImage())));
        ImageView profileImage = new ImageView(image);
        profileImage.setScaleX(0.3);
        profileImage.setScaleY(0.3);
        profileImage.setLayoutX(27);
        profileImage.setLayoutY(-10+(i*75));


        Rectangle rectangle = new Rectangle(420 , 70);
        rectangle.setLayoutX(30);
        rectangle.setLayoutY(55+(i*75));
        if(i==1){
            rectangle.setFill(Color.valueOf("#dbbc74"));
        }
        else if(i==2){
            rectangle.setFill(Color.valueOf("#adaaa3"));
        }
        else if(i==3){
            rectangle.setFill(Color.valueOf("#e66f40"));
        }
        else {
            rectangle.setFill(Color.valueOf("#d5ebf0"));
        }

        board.getChildren().addAll(rectangle , rank ,username , score , profileImage);

    }

    private void sortUserByScore(){
        User.getAllUsers().sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int comper = 0;
                if(o1.getScore() != o2.getScore()){
                    if(o1.getScore() < o2.getScore()){
                        return 1;
                    }
                    else {
                        return -1;
                    }
                }

                return 0;
            }
        });
    }

    public void goMainMenu(MouseEvent mouseEvent) throws IOException {
        App.setRoot("mainmenu");
    }
}
