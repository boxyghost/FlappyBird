module brynn.javafx.flappyBird {
    requires javafx.controls;
    requires javafx.fxml;

    opens brynn.javafx.flappyBird to javafx.fxml;
    exports brynn.javafx.flappyBird;
}
