package test;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class BaseClass extends Application{

//    @Override
//    public void start(Stage stage) throws Exception {}

    //event functions
    public void endSystem(KeyEvent event) {
        if (event.getCode() == KeyCode.C && event.isControlDown()) {
            System.exit(0);
        }
    }
}
