package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MiniBoss extends ImageView{


    private int health  ;


    public MiniBoss(Image image){
        super(image);
        this.health = 2;
    }

    public void move(double dx, double dy){
        this.setLayoutX(this.getLayoutX() + dx);
        this.setLayoutY(this.getLayoutY() + dy);
    }


    public boolean hasCollision(ImageView enemy){
        return enemy.getBoundsInParent().intersects(MiniBoss.this.getBoundsInParent());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
