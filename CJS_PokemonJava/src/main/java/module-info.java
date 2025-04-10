module com.example.cjs_pokemonjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cjs_pokemonjava to javafx.fxml;
    exports com.example.cjs_pokemonjava;
}