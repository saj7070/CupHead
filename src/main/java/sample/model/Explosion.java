package sample.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.Transitions.ExplosionTransition;
import sample.view.GameBoard;

public class Explosion extends ImageView {


    public Explosion(Image image){
        super(image);
    }


    public static Explosion makeExplosion(double x , double y ){
        Image image = new Image(Explosion.class.getResource("/assets/images/explosion/0.png").toString());
        Explosion explosion = new Explosion(image);
        explosion.setLayoutX(x);
        explosion.setLayoutY(y);
        return explosion;

    }

    public static void activeExplosionTransition(Explosion explosion , AnchorPane pane ) {
        ExplosionTransition animation = new ExplosionTransition(explosion);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(explosion);
            }
        });
        animation.play();
    }




}
