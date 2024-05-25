module com.example.memory_seva1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.memory_seva1 to javafx.fxml;
    exports com.example.memory_seva1;
}