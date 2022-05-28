package sample.Transitions;

import javafx.animation.Transition;
import javafx.application.Platform;
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
import sample.model.*;
import sample.view.GameBoard;

import java.io.IOException;

public class BulletTransition extends Transition {

    private Bullet bullet;

    private ImageView boss;
    private AnchorPane pane;
    private int number = 10;

    private boolean changeColor = true;

    public BulletTransition(Bullet bullet, ImageView boss ,  AnchorPane pane) {
        this.bullet = bullet;
        this.boss = boss;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {

        double dx = number;
        double dy = 0;
        bullet.move(dx , dy);
        if(bullet.hasCollision(boss)){
            Boss.getInstance().setHealth(Boss.getInstance().getHealth()-1);
            GameBoard.progressBar.setProgress((double) Boss.getInstance().getHealth()/Boss.getInstance().getMaxHealth());
            if(changeColor && GameBoard.progressBar.getProgress()< 0.5){
                changeProgressBarColor();
                changeColor = false;
            }
            GameBoard.progressLable.setText(Integer.toString(Boss.getInstance().getHealth()));
            if(Boss.getInstance().getHealth() == Boss.getInstance().getMaxHealth()/2){
                Image image = new Image(getClass().getResource("/assets/images/2.png").toString());
                boss.setImage(image);
                Boss.setPhase(2);

            }
            if(GameController.getInstance().checkFinishGame()){
                this.stop();
                showFinishWindow();
                return;
            }
            number = -200;
            bullet.setImage(null);
            pane.getChildren().remove(bullet);
            Explosion explosion = Explosion.makeExplosion(bullet.getLayoutX() , bullet.getLayoutY());
            pane.getChildren().add(explosion);
            Explosion.activeExplosionTransition(explosion ,pane);



        }
        for (Node node :pane.getChildren()) {
            if(node instanceof MiniBoss && bullet.hasCollision((MiniBoss)node)){

                number = -1000;
                pane.getChildren().remove(bullet);
                ((MiniBoss) node).setHealth(((MiniBoss) node).getHealth() - 1);
                if( ((MiniBoss) node).getHealth() == 0){
                    node.setLayoutX(1200);
                    pane.getChildren().remove(node);

                }
                break;


            }
        }
        if(GameController.getInstance().checkFinishGame()){
            this.stop();
        }

    }
    private void showFinishWindow(){
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

        Image image3 = new Image(getClass().getResource("/assets/images/victory.png").toString());
        ImageView victory = new ImageView(image3);
        victory.setLayoutX(405);
        victory.setLayoutY(185);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(1-(double) Boss.getInstance().getHealth()/Boss.getInstance().getMaxHealth());
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

        int receivedScore = (Boss.getInstance().getMaxHealth()-Boss.getInstance().getHealth())*10;
        Label score = new Label();
        score.setText("Youre received score  |  "  + receivedScore);
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



        pane.getChildren().addAll(border , retryBtn ,backBtn,GameBoard.timer , progressBar , score ,flag ,victory);
    }

    private static void changeProgressBarColor(){
        GameBoard.progressBar.setStyle("-fx-accent: #9f0505");
    }
}
