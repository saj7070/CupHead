package sample.controller;

import javafx.animation.Timeline;
import sample.model.Boss;
import sample.model.Heart;
import sample.view.GameBoard;

import java.util.PropertyResourceBundle;

public class GameController {

    private static GameController gameController;
    private GameBoard gameBoard;


    private GameController(){

    }

    public static GameController getInstance(){
        if(gameController == null){
            gameController = new GameController();
        }
        return gameController;
    }

    public boolean checkFinishGame(){

        if(Heart.getAllHearts().size() == 0 ){

            stopAllAnimation();
            return true;
        }
        else if(Boss.getInstance().getHealth() == 0){

            stopAllAnimation();
            return true;
        }

        return false;

    }

    public static void stopAllAnimation(){
        for (Timeline timeline : AnimationController.getTimelines()) {
            timeline.stop();
        }
    }
}
