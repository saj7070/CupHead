package sample.Transitions;

import javafx.animation.Transition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.GameController;
import sample.model.Background;
import sample.model.Game;
import sample.view.GameBoard;

public class BackgroundTransition extends Transition {


    private Background background;
    private AnchorPane pane;

    public BackgroundTransition(Background background , AnchorPane pane){
        this.background = background;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {

        if(GameController.getInstance().checkFinishGame()){
            this.stop();
            return;
        }
        background.move(-2 , 0);

        long elapsedTime = System.currentTimeMillis() - GameBoard.startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;

        GameBoard.timer.setText(elapsedMinutes + " : " + secondsDisplay);




    }

}
