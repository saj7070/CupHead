package sample.Transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.GameController;
import sample.model.BossEgg;
import sample.model.CupHead;
import sample.model.Explosion;
import sample.model.Heart;

public class BossShootTransition extends Transition {

    private BossEgg bossEgg;
    private ImageView cupHead;
    private AnchorPane pane;
    private int number = -3;

    public BossShootTransition(BossEgg bossEgg , ImageView cupHead , AnchorPane pane){
        this.bossEgg = bossEgg;
        this.cupHead = cupHead;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(3000));
        this.setCycleCount(1);

    }

    @Override
    protected void interpolate(double v) {
        double dx = number;
        double dy = 0;
        bossEgg.move(dx , dy);
        if(!CupHead.getInstance().isBlink() && bossEgg.hasCollision(cupHead)){
            number = 2000;
            pane.getChildren().remove(bossEgg);

            Explosion explosion = Explosion.makeExplosion(bossEgg.getLayoutX() , bossEgg.getLayoutY());
            pane.getChildren().add(explosion);
            Explosion.activeExplosionTransition(explosion ,pane );

            CupHead.getInstance().resetCupHeadHealth(pane);
        }


    }


}
