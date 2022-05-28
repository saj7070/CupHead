package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boss extends ImageView {

    private static Boss boss;
    private static int phase;


    private int maxHealth ;
    private int health = 100;




    private Boss(Image image){
        super(image);
        phase = 1;


    }
    public static Boss getInstance(){
        if(boss == null){
            Image image = new Image(Boss.class.getResource("/assets/images/1.png").toString());
            boss = new Boss(image);
            boss.setLayoutX(400);
            boss.setLayoutY(200);
            boss.setScaleX(0.7);
            boss.setScaleY(0.7);

        }

        return boss;
    }

    public static void move(double dx, double dy){
        Boss.boss.setLayoutX(Boss.boss.getLayoutX() + dx);
        Boss.boss.setLayoutY(Boss.boss.getLayoutY() + dy);
    }
    public static int checkHardShipDegree(){
        if(Game.getInstance().getHardshipLevel().equals("easy")){
            return 50;
        }
        else if(Game.getInstance().getHardshipLevel().equals("hard")){
            return 150;
        }
        else{
            return 100;
        }
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public static void setPhase(int phase) {
        Boss.phase = phase;
    }

    public static int getPhase() {
        return phase;
    }
}
