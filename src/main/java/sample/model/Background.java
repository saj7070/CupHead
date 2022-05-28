package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background extends ImageView {

    public Background(Image image){
        super(image);
    }


    public void move(double dx, double dy){
        this.setLayoutX(this.getLayoutX() + dx);
        this.setLayoutY(this.getLayoutY() + dy);
    }
}
