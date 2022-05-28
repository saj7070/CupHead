package sample.Transitions;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
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
import sample.model.Boss;
import sample.model.Game;
import sample.view.GameBoard;

import java.io.IOException;

public class BossMoveTransition extends Transition {

    public ImageView boss;
    double numberX = 0.5;
    double numberY = 0.5;
    private static BossMoveTransition bossMoveTransition;
    private final AnchorPane pane;

    private BossMoveTransition(ImageView boss  , AnchorPane pane){
        this.pane = pane;
        this.boss = Boss.getInstance();
        this.setCycleDuration(Duration.millis(1000));

        this.setCycleCount(-1);

    }

   public static BossMoveTransition getInstance(AnchorPane pane){
       if(bossMoveTransition == null){
           bossMoveTransition = new BossMoveTransition(Boss.getInstance() , pane);
       }
       return bossMoveTransition;
   }
    @Override
    protected void interpolate(double v) {

        if(Boss.getPhase()==1){
            phase1Movement();
        }
        else {
            phase2Movement();
        }

        
        Boss.move(numberX , numberY);
        if(GameController.getInstance().checkFinishGame()){
            this.stop();
        }

    }

    private void phase1Movement(){
        if(boss.getLayoutY() > 400){
            numberY = -0.9;
        }
        if(boss.getLayoutY() < -200){
            numberY = 0.9;
        }
        if(boss.getLayoutX() > 700){
            numberX = -0.9;
        }
        if(boss.getLayoutX() < 500){
            numberX = 0.9;
        }
    }

    private void phase2Movement(){
        if(boss.getLayoutY() > 480){
            numberY = -0.9;
        }
        if(boss.getLayoutY() < -10){
            numberY = 0.9;
        }
        if(boss.getLayoutX() > 900){
            numberX = -0.9;
        }
        if(boss.getLayoutX() < 300){
            numberX = 0.9;
        }
    }

}
