module com.example.tester {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tester to javafx.fxml;
    exports com.example.tester;
}