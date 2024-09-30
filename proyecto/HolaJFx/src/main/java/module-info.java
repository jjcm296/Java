module org.example.holajfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.holajfx to javafx.fxml;

    exports org.example.holajfx.entidades;
    opens org.example.holajfx.entidades to javafx.fxml;

    exports org.example.holajfx.entorno;
    opens org.example.holajfx.entorno to javafx.fxml;

    exports org.example.holajfx.juego;
    opens org.example.holajfx.juego to javafx.fxml;
}