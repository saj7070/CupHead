package sample.view;

import javafx.scene.input.MouseEvent;
import sample.App;

import java.io.IOException;

public class Guide {
    public void goMainMenu(MouseEvent mouseEvent) throws IOException {

        App.setRoot("mainmenu");
    }
}
