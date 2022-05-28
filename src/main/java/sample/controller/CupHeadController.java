package sample.controller;

import javafx.scene.image.ImageView;

public class CupHeadController {

    public static void moveRight(ImageView cuphead){
        if(hitRightWall(cuphead)){
            cuphead.setLayoutX(cuphead.getLayoutX() + 20);
        }

    }
    public static void moveLeft(ImageView cuphead){
        if(hitLeftWall(cuphead)){
            cuphead.setLayoutX(cuphead.getLayoutX() - 20);
        }


    }
    public static void moveUp(ImageView cuphead){
        if(hitUpWall(cuphead)){
            cuphead.setLayoutY(cuphead.getLayoutY() - 20);

        }

    }
    public static void moveDown(ImageView cuphead){
        if(hitDownWall(cuphead)){
            cuphead.setLayoutY(cuphead.getLayoutY() + 20);
        }
    }
    public static boolean hitRightWall(ImageView cuphead){
        if(cuphead.getLayoutX() + cuphead.getFitWidth() > 1100){
            return false;
        }
        return true;

    }
    public static boolean hitLeftWall(ImageView cuphead){
        if(cuphead.getLayoutX() < 0){
            return false;
        }
        return true;

    }
    public static boolean hitUpWall(ImageView cuphead){
        if(cuphead.getLayoutY() < 0){
            return false;
        }
        return true;
    }
    public static boolean hitDownWall(ImageView cuphead){
        if(cuphead.getLayoutY() + cuphead.getFitHeight()> 680){
            return false;
        }
        return true;

    }
}
