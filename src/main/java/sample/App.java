package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.controller.GsonHandler;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Parent pane;

    static {
        try {
            pane = loadFXML("loginmenu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageView rec;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("CupHead");
        Image icon = new Image(getClass().getResource("/assets/images/pro.png").toString());
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.show();


    }

    public static void setRoot(String fxml) throws IOException {
        pane = loadFXML(fxml);
        scene.setRoot(pane);

    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "fxml/" +fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        GsonHandler.importDataOfUser();
        launch();
    }

}