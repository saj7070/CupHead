package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.Timer;
import java.util.function.ToIntBiFunction;

public class AnimationController {

    private static ArrayList<Timeline> timelines = new ArrayList<>();

    private static AnimationController animationController;

    private AnimationController(){

    }

    public static AnimationController getInstance(){
        if(animationController == null){
            animationController = new AnimationController();
        }
        return animationController;
    }


    public static ArrayList<Timeline> getTimelines() {
        return timelines;
    }
}
