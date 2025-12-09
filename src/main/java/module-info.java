module com.example.kr3v8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kr3v8 to javafx.fxml;
    exports com.example.kr3v8;
}