package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ButtonTest extends Application {
    
    Button b1, b2;
    
    final private int B1_WIDTH = 80;
    final private int B1_HEIGHT = 20;
    final private int B2_WIDTH = 100;
    final private int B2_HEIGHT = 30;

    private boolean b1Swicth = false;
    private boolean b2Swicth = false;

    public static void main(String[]args) { Application.launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Button Test");
        stage.setHeight(1000);
        stage.setWidth(1000);

        VBox pane = new VBox();

        b1 = new Button("off");
        b2 = new Button("off");
        b1.setFont(new Font(12));
        b2.setFont(new Font(14));
        b1.setPrefWidth(B1_WIDTH);
        b1.setPrefHeight(B1_HEIGHT);
        b2.setPrefWidth(B2_WIDTH);
        b2.setPrefHeight(B2_HEIGHT);
        b1.setOnAction(event -> b1TextUpdate(event));
        b2.setOnAction(event -> b2TextUpdate(event));

        pane.getChildren().addAll(b1, b2);

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> endSystem(event));

        stage.setScene(scene);
        stage.show();

    }
    //events functions
    private void b1TextUpdate(ActionEvent event) {
        if (b1Swicth) {
            b1.setText("OFF");
            b1Swicth = false;
        } else {
            b1.setText("ON");
            b1Swicth = true;
        }
    }
    private void b2TextUpdate(ActionEvent event) {
        if (b2Swicth) {
            b2.setText("OFF");
            b2Swicth = false;
        } else {
            b2.setText("ON");
            b2Swicth = true;
        }
    }
    public void endSystem(KeyEvent event) {
        if (event.getCode() == KeyCode.C && event.isControlDown()) {
            System.exit(0);
        }
    }
}
