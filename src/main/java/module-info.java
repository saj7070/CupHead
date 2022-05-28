module sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens sample to javafx.fxml;
    exports sample;
    exports sample.view;
    opens sample.view to javafx.fxml;
    opens sample.model to com.google.gson;
}
