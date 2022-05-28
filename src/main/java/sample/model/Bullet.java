package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {


    public Bullet(Image image) {
        super(image);
    }


    public void move(double dx, double dy){
        this.setLayoutX(this.getLayoutX() + dx);
        this.setLayoutY(this.getLayoutY() + dy);
    }

    public boolean hasCollision(ImageView enemy){
        return enemy.getBoundsInParent().intersects(this.getBoundsInParent());
    }

}
