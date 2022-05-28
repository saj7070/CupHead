package sample.view;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sample.App;
import sample.Transitions.*;
import sample.controller.AnimationController;
import sample.controller.CupHeadController;
import sample.controller.GameController;
import sample.model.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class GameBoard {


    public ImageView cuphead;
    public AnchorPane pane;
    public static long startTime = System.currentTimeMillis();
    public static Label timer;

    public static ProgressBar progressBar;

    public static Label progressLable;




    public void initialize() {

        initializeEntities();


        setBackground(8,0,0,1);

        timerOfBackGroundTransition();

        makeBoss();

        showHeart();
        activeShootBossEgg();
        activeMiniBossAnimation();

        showProgressBar();
        showTimer();

        BossMoveTransition.getInstance(pane).play();





        Platform.runLater(() ->cuphead.requestFocus());

        cuphead.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case A:
                        CupHeadController.moveLeft(cuphead);
                        break;
                    case D:
                        CupHeadController.moveRight(cuphead);
                        break;
                    case W:
                        CupHeadController.moveUp(cuphead);
                        break;
                    case S:
                        CupHeadController.moveDown(cuphead);
                        break;
                    case SPACE:
                        shootBullet();

                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void shootBullet() {
        Image image = new Image(getClass().getResource("/assets/images/bullet.png").toString());
        Bullet bullet = new Bullet(image);
        bullet.setLayoutX(cuphead.getLayoutX() + 48);
        bullet.setLayoutY(cuphead.getLayoutY() + 47);
        pane.getChildren().add(bullet);
        bulletAnimations(bullet, Boss.getInstance());

    }

    private void bulletAnimations(Bullet bullet, ImageView boss ) {
        BulletTransition animation = new BulletTransition(bullet, boss ,pane);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(bullet);
            }
        });
        animation.play();

    }

    private void bossEggAnimation(BossEgg bossEgg) {
        BossShootTransition animation = new BossShootTransition(bossEgg , cuphead ,pane);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(bossEgg);
            }
        });
        animation.play();


    }

    private void makeBossEgg() {
        Image image;
        BossEgg bossEgg;
        if(Boss.getPhase()==1){
            image = new Image(getClass().getResource("/assets/images/egg.png").toString());
            bossEgg = new BossEgg(image);
            bossEgg.setLayoutX(Boss.getInstance().getLayoutX() + 70);
            bossEgg.setLayoutY(Boss.getInstance().getLayoutY() + 195);
            bossEgg.setScaleX(0.5);
            bossEgg.setScaleY(0.5);
        }
        else {
            image = new Image(getClass().getResource("/assets/images/bullet2.png").toString());
            bossEgg = new BossEgg(image);
            bossEgg.setLayoutX(Boss.getInstance().getLayoutX() + 50);
            bossEgg.setLayoutY(Boss.getInstance().getLayoutY() + 76);
            bossEgg.setScaleX(0.8);
            bossEgg.setScaleY(0.8);
        }

        pane.getChildren().add(bossEgg);
        bossEggAnimation(bossEgg);

    }

    private void shootBossEgg() {

        Random random = new Random();
        int number = random.nextInt(4) + 1;
        int count  = random.nextInt(5)+5;


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(number), ec -> {

            makeBossEgg();
        }));
        AnimationController.getTimelines().add(timeline);
        timeline.setDelay(Duration.millis(number*1000));
        timeline.setCycleCount(count);
        timeline.play();

    }

    private void activeShootBossEgg(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(7), ec -> {

            shootBossEgg();

        }));
        AnimationController.getTimelines().add(timeline);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void makeBoss() {
        pane.getChildren().add(Boss.getInstance());
    }

    private void makeMiniBoss(int x , int y){
        Image image = new Image(getClass().getResource("/assets/images/yellowminiboss/1.png").toString());
        MiniBoss miniBoss = new MiniBoss(image);
        miniBoss.setLayoutX(x);
        miniBoss.setLayoutY(y);
        miniBoss.setScaleX(0.8);
        miniBoss.setScaleY(0.8);
        pane.getChildren().add(miniBoss);
        miniBossAnimation(miniBoss);

    }
    private void miniBossAnimation(MiniBoss miniBoss ){
        MiniBossMoveTransition animation = new MiniBossMoveTransition(miniBoss , cuphead ,pane);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(miniBoss);
            }
        });
        animation.play();
    }

    private void activeMiniBossAnimation(){


        Random random = new Random();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(25), ec -> {  // 25

            int randomNumber = (random.nextInt(5)+1)*100;

            Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), eec -> {
                    makeMiniBoss(1200,randomNumber);

            }));
            AnimationController.getTimelines().add(timeline2);
            timeline2.setCycleCount(4);
            timeline2.play();

        }));
        AnimationController.getTimelines().add(timeline);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setBackground(int number , int x ,int y , int layer){
        Image image = new Image(getClass().getResource("/assets/images/backgrounds/"+number+".png").toString());
        Background background = new Background(image);
        background.setLayoutX(x);
        background.setLayoutY(y);
        pane.getChildren().add(layer, background);
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(pane.getChildren());
        Collections.swap(workingCollection, layer-1, layer);
        pane.getChildren().setAll(workingCollection);




        if(number != 8){

            activeBackgroundTransition(background);
        }

    }

    private void activeBackgroundTransition(Background background){

        BackgroundTransition animation = new BackgroundTransition(background , pane);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(background);
            }
        });
        animation.play();

    }

    private void timerOfBackGroundTransition() {

        setBackground(7,0,-10,2);
        setBackground(6,0,100,3);
        setBackground(5,0,420,4);
        setBackground(4,0,437,5);
        setBackground(2,0,565,6);
        setBackground(3,0,605,7);
        setBackground(1,0,545,8);


        Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(2.3), eec -> {
            if(GameController.getInstance().checkFinishGame()){
                return;
            }

            setBackground(7,1200,-10,2);
            setBackground(6,1200,100,3);
            setBackground(5,1200,420,4);
            setBackground(4,1200,437,5);
            setBackground(2,1200,565,6);
            setBackground(3,1200,605,7);
            setBackground(1,1200,545,8);

        }));
        AnimationController.getTimelines().add(timeline3);

        timeline3.setCycleCount(1);
        timeline3.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(6), ev -> {

            if(GameController.getInstance().checkFinishGame()){
                return;
            }

                setBackground(7,1200,-10,2);
                setBackground(6,1200,100,3);
                setBackground(5,1200,420,4);
                setBackground(4,1200,437,5);
                setBackground(2,1200,565,6);
                setBackground(3,1200,605,7);
                setBackground(1,1200,545,8);

        }));
        AnimationController.getTimelines().add(timeline2);

        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();

    }

    private void showHeart(){
        pane.getChildren().addAll( Heart.getAllHearts());

    }
    private void showProgressBar(){
        progressBar = new ProgressBar();
        progressLable = new Label();
        Image image = new Image(getClass().getResource("/assets/images/bheart.png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(770);
        imageView.setLayoutY(14);

        progressLable.setText(Integer.toString(Boss.getInstance().getHealth()));
        progressLable.setScaleX(2);
        progressLable.setScaleY(2);
        progressLable.setStyle("-fx-text-fill: #3f3f93 ; -fx-font-weight: bold ; -fx-font-family: 'Arial Rounded MT Bold'");
        progressLable.setLayoutX(1030);
        progressLable.setLayoutY(35);

        progressBar.setLayoutX(860);
        progressBar.setLayoutY(30);
        progressBar.setScaleX(2);
        progressBar.setScaleY(2);
        progressBar.setProgress(1);
        progressBar.setStyle("-fx-accent: green ; -fx-border-color: transparent ; -fx-border-width: 5 ; -fx-border-radius: 20 ; ");
        pane.getChildren().addAll(progressBar , progressLable ,imageView);

    }

    private void showTimer(){
        timer = new Label();
        timer.setLayoutX(1000);
        timer.setLayoutY(625);
        timer.setScaleX(1.8);
        timer.setScaleY(1.8);
        timer.setStyle("-fx-font-family: 'Arial Rounded MT Bold' ; -fx-text-fill: #030d33 ; -fx-background-color: #8b9fa9 ;" +
                " -fx-padding: 5 ; -fx-background-radius: 10 ; -fx-background-size: 10");
        pane.getChildren().add(timer);
    }


    private void initializeEntities(){
        AnimationController.getTimelines().clear();
        Heart.fillCupHeadHearts();
        Boss.getInstance().setMaxHealth(Boss.checkHardShipDegree());
        Image image = new Image(getClass().getResource("/assets/images/1.png").toString());
        Boss.getInstance().setImage(image);
        Boss.setPhase(1);
        Boss.getInstance().setHealth(Boss.checkHardShipDegree());
        startTime = System.currentTimeMillis();
    }

    public void goMainMenu(MouseEvent mouseEvent) throws IOException {
        Boss.getInstance().setHealth(0);
        Platform.runLater(() -> {
            try {
                App.setRoot("mainmenu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
