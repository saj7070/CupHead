package sample.Transitions;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import sample.model.Explosion;

public class ExplosionTransition extends Transition {

    private Explosion explosion;

    public ExplosionTransition(Explosion explosion){
        this.explosion = explosion;
        this.setCycleDuration(Duration.millis(500));
    }
    @Override
    protected void interpolate(double v) {
        int frame = (int)Math.floor(v*6);
        Image image = new Image(Explosion.class.getResource("/assets/images/explosion/"+frame+".png").toString());
        explosion.setImage(image);


    }
}
