module org.example.banco {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.banco to javafx.fxml;
    exports org.example.banco;
}