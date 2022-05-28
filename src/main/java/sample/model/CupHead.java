package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.App;
import sample.controller.GameController;
import sample.view.GameBoard;

import java.io.IOException;

public class CupHead extends ImageView {

    private boolean isBlink = false;

    private static CupHead cupHead;


    private CupHead() {

    }

    public static CupHead getInstance() {
        if (cupHead == null) {
            cupHead = new CupHead();
            return cupHead;
        }
        return cupHead;
    }

    public void resetCupHeadHealth(AnchorPane pane) {
        if (!isBlink) {
            ImageView tempNode = null;
            pane.getChildren().remove(Heart.getAllHearts().get(Heart.getAllHearts().size() - 1));
            Heart.getAllHearts().remove(Heart.getAllHearts().size() - 1);
            if (Heart.getAllHearts().size() == 1) {
                Image image = new Image(getClass().getResource("/assets/images/alarm.png").toString());
                ImageView alarm = new ImageView(image);
                alarm.setLayoutX(-40);
                alarm.setLayoutY(470);
                pane.getChildren().add(alarm);
           }
            if (Heart.getAllHearts().size() == 0) {
                showFinishWindow(pane);
            }


            for (Node node : pane.getChildren()) {
                if (node.getId() != null && node instanceof ImageView && node.getId().equals("cuphead")) {
                    node.setStyle("-fx-opacity: 0.5");
                    tempNode = (ImageView) node;
                    break;
                }
            }
            isBlink = true;
            ImageView finalTempNode = tempNode;
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), eec -> {
                finalTempNode.setStyle("-fx-opacity: 1");
                isBlink = false;
            }));
            timeline.setCycleCount(1);
            timeline.play();

        }
    }


    public void setBlink(boolean blink) {
        isBlink = blink;
    }

    public boolean isBlink() {
        return isBlink;
    }

    private void showFinishWindow(AnchorPane pane) {
        Image image = new Image(getClass().getResource("/assets/images/winscreen_board.png").toString());
        ImageView border = new ImageView(image);
        border.setLayoutX(280);
        border.setLayoutY(130);

        Button retryBtn = new Button();
        retryBtn.setLayoutX(535);
        retryBtn.setLayoutY(390);
        retryBtn.setScaleX(2);
        retryBtn.setScaleY(1.8);
        retryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    App.setRoot("game");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        retryBtn.setText("Retry");

        Image image3 = new Image(getClass().getResource("/assets/images/lose.png").toString());
        ImageView victory = new ImageView(image3);
        victory.setRotate(-40);
        victory.setLayoutX(200);
        victory.setLayoutY(125);

        Image image4 = new Image(getClass().getResource("/assets/images/bosshead.png").toString());
        ImageView boss = new ImageView(image4);
        boss.setLayoutX(580);
        boss.setLayoutY(100);

        Label quote = new Label();
        quote.setText("Giving up is the only\n   sure way to fail :)");
        quote.setStyle("-fx-font-size: 18 ; -fx-font-family: 'Arial Rounded MT Bold' ; -fx-text-fill: #89f6e7 ; -fx-spacing: 50");
        quote.setLayoutX(375);
        quote.setLayoutY(210);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(1 - (double) Boss.getInstance().getHealth() / Boss.getInstance().getMaxHealth());
        progressBar.setLayoutX(495);
        progressBar.setLayoutY(305);
        progressBar.setScaleX(3.7);
        progressBar.setScaleY(1.8);
        progressBar.setStyle("-fx-accent: blue  ; -fx-border-width: 5 ; -fx-border-radius: 20 ; ");

        Image image2 = new Image(getClass().getResource("/assets/images/flag.png").toString());
        ImageView flag = new ImageView(image2);
        flag.setLayoutX(685);
        flag.setLayoutY(255);
        flag.setRotate(25);

        int receivedScore = (Boss.getInstance().getMaxHealth() - Boss.getInstance().getHealth()) * 10;
        Label score = new Label();
        score.setText("Youre received score  |  " + receivedScore);
        score.setLayoutX(425);
        score.setLayoutY(340);
        score.setStyle("-fx-font-family: 'Arial Rounded MT Bold' ; -fx-font-size: 20 ; -fx-text-fill: #e0dc00");
        Game.getInstance().getCurrentUser().setScore(Game.getInstance().getCurrentUser().getScore() +
                receivedScore);

        Button backBtn = new Button();
        backBtn.setLayoutX(505);
        backBtn.setLayoutY(440);
        backBtn.setScaleX(2);
        backBtn.setScaleY(1.8);
        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    App.setRoot("mainmenu");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        backBtn.setText("Return to Menu");

        GameBoard.timer.setText("Time elapsed  |  " + GameBoard.timer.getText());
        GameBoard.timer.setLayoutX(490);
        GameBoard.timer.setLayoutY(500);
        pane.getChildren().remove(GameBoard.timer);

        pane.getChildren().addAll(border, retryBtn, backBtn, GameBoard.timer, progressBar, score, flag, victory, boss, quote);
    }
}
