package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Heart extends ImageView {

    private static int maxHealth = 5;

    private static ArrayList<Heart> allHearts = new ArrayList<>();

    static {
        chekHardshipDegree();
        for (int i = 0 ; i < maxHealth; i++){
            Image image = new Image(Heart.class.getResource("/assets/images/heart.png").toString());
            Heart heart = new Heart(image);
            heart.setLayoutX(i*40 + 50);
            heart.setLayoutY(600);
            Heart.getAllHearts().add(heart);
        }
    }

    public Heart(Image image){
        super(image);
    }


    public static void fillCupHeadHearts(){
        Heart.getAllHearts().clear();
        Image image = new Image(Heart.class.getResource("/assets/images/heart.png").toString());
        chekHardshipDegree();
        for (int i = 0 ; i < maxHealth; i++){
            Heart heart = new Heart(image);
            heart.setLayoutX(i*40 + 50);
            heart.setLayoutY(600);
            Heart.getAllHearts().add(heart);
        }
    }

    private static void chekHardshipDegree(){
        if(Game.getInstance().getHardshipLevel().equals("easy")){
            maxHealth = 10;
        }
        else if(Game.getInstance().getHardshipLevel().equals("hard")){
            maxHealth = 2;
        }
        else{
            maxHealth = 5;
        }
    }
    public static ArrayList<Heart> getAllHearts() {
        return allHearts;
    }


}
