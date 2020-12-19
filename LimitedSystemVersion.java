/*
//
// 12月22日に提出予定の機能限定版のソースコード
//
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LimitedSystemVersion extends Application {

    //object
    private HashMap<Shape,Boolean> shapeMap;

    //field variable
    final private int FLOORWIDTH = 1600;    //the width of the stage
    final private int FLOORHEIGHT = 1000;   //the height of the stage

    //main function
    public static void main(String[]args) { Application.launch(args); }


    //start function
    @Override
    public void start(Stage stage) throws Exception {

        //stage settings
        stage.setTitle("Limited Edition");
        stage.setWidth(FLOORWIDTH);
        stage.setHeight(FLOORHEIGHT);

        //menu settings
        MenuBar manuBar = new MenuBar();
        Menu generateMenu = new Menu("Generate");
        MenuItem menuCircle = new MenuItem("Circle");
        MenuItem menuRectangle = new MenuItem("Rectangle");
        MenuItem menuEllipse = new MenuItem("Ellipse");

        //loop processing
        Timeline timeline = new Timeline(
            new KeyFrame(Duration(16.7), event -> )
        )
    }

    // /* event functions */ //

    // menu functions //
    //


    // system settings //
    //forced termination
    private void endSystem(KeyEvent event) {
        if (event.getCode() == KeyCode.C && event.isControlDown()) {
            System.exit(0);
        }
    }
}