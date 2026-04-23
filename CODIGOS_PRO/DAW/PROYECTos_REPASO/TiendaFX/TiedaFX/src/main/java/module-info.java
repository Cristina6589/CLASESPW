module org.example.tiedafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens org.example.tiedafx to javafx.fxml;
    exports org.example.tiedafx;
    exports org.example.tiedafx.controller;
    opens org.example.tiedafx.controller to javafx.fxml;
    opens org.example.tiedafx.dao to java.sql;
    exports org.example.tiedafx.dao;

    opens org.example.tiedafx.model to java.sql;
    exports org.example.tiedafx.model;

    opens org.example.tiedafx.database to java.sql;
    exports org.example.tiedafx.database;
}