module org.example.holajfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.holajfx to javafx.fxml;
    exports org.example.holajfx;
}