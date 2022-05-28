package sample.Transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.GameController;
import sample.model.Bullet;
import sample.model.CupHead;
import sample.model.Explosion;
import sample.model.MiniBoss;

public class MiniBossMoveTransition extends Transition {

    private MiniBoss miniBoss;
    private ImageView cupHead;
    private AnchorPane pane;

    public MiniBossMoveTransition(MiniBoss miniBoss  , ImageView cupHead, AnchorPane pane){
        this.miniBoss = miniBoss;
        this.cupHead = cupHead;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(20);
    }


    @Override
    protected void interpolate(double v) {

        int frame = (int)Math.floor(v*15)+1;
        Image image = new Image(MiniBossMoveTransition.class.getResource("/assets/images/yellowminiboss/"+frame+".png").toString());
        miniBoss.setImage(image);
        miniBoss.move(-0.9 , 0.05);
        if(!CupHead.getInstance().isBlink() && miniBoss.hasCollision(cupHead)){

            miniBoss.move(1200 , 0);
            pane.getChildren().remove(miniBoss);
            Explosion explosion = Explosion.makeExplosion(cupHead.getLayoutX() , cupHead.getLayoutY());
            pane.getChildren().add(explosion);
            Explosion.activeExplosionTransition(explosion ,pane);
            CupHead.getInstance().resetCupHeadHealth(pane);

        }
        if(GameController.getInstance().checkFinishGame()){
            this.stop();
        }



    }
}
