module com.example.miniproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miniproject to javafx.fxml;
    exports com.example.miniproject;
}