module com.example.new_com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.google.gson;





    opens com.example.tester to javafx.fxml;
    exports com.example.tester;
}