module com.example.javafx_ecommerce_store {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javafx_ecommerce_store to javafx.fxml;
    exports com.example.javafx_ecommerce_store;
}