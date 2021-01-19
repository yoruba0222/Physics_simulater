/*
//
// 12月22日に提出予定の機能限定版のソースコード
//
*/

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LimitedSystemVersion extends Application {

    //object
    private Timeline timeline;
    private Drag drag;
    private Pane root;
    private PhysicsPipeline pipeline;
    private ArrayList<ExpandedCircle> circleList;
    private ArrayList<ExpandedRectangle> rectList;

    final private double INIT_RADIUS = 50;
    final private double INIT_CENTER_X = 100;
    final private double INIT_CENTER_Y = 100;
    final private double INIT_WIDTH = 100;
    final private double INIT_HEIGHT = 100;

    //field variable
    final private int FLOORWIDTH = 1400;    //the width of the stage
    final private int FLOORHEIGHT = 100;   //the height of the stage

    //main function
    public static void main(String[]args) { Application.launch(args); }

    //start function
    @Override
    public void start(Stage stage) throws Exception {

        //stage settings
        stage.setTitle("Limited Edition");
        stage.setWidth(FLOORWIDTH);
        stage.setHeight(FLOORHEIGHT);

        root = new Pane();
        drag = new Drag();
        pipeline = new PhysicsPipeline();
        circleList = new ArrayList<>();
        rectList = new ArrayList<>();

        //menu settings
        MenuBar menuBar = new MenuBar();
        Menu generateMenu = new Menu("Generate");
        MenuItem menuCircle = new MenuItem("Circle");
        MenuItem menuRectangle = new MenuItem("Rectangle");
        MenuItem menuEllipse = new MenuItem("Ellipse");


        generateMenu.getItems().addAll(menuCircle,menuEllipse,menuRectangle);
        menuBar.getMenus().addAll(generateMenu);
        root.getChildren().add(menuBar);

        Scene scene = new Scene(root);

        //setting event function
        menuCircle.setOnAction(event -> generateCircle());
        menuRectangle.setOnAction(event -> generateRectangle());
        scene.setOnMouseClicked(event -> drag.moveObject(event));
        scene.setOnMouseDragged(event -> drag.moveObject(event));
        scene.setOnMouseReleased((event -> drag.releaseObject(event)));
        //scene.setOnKeyPressed(event -> stopSimulation(event));
        //scene.setOnKeyPressed(event -> playSimulation(event));
        scene.setOnKeyPressed(event -> keySystem(event));
        //scene.setOnKeyPressed(event -> playSimulation(event));

        stage.setScene(scene);
        stage.show();

        //loop processing
        timeline = new Timeline(
            new KeyFrame(Duration.millis(100), event -> pipeline.update(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    // /* event functions */ //

    // menu functions //
    private void generateCircle() {
        ExpandedCircle circle = new ExpandedCircle(INIT_CENTER_X, INIT_CENTER_Y, INIT_RADIUS);
        root.getChildren().add(circle);
        circleList.add(circle);
        drag.setDragedCircle(circle);
        pipeline.addPhysicalShape(circle);
    }
    private void generateRectangle() {
        ExpandedRectangle rect = new ExpandedRectangle(INIT_CENTER_X, INIT_CENTER_Y, INIT_WIDTH, INIT_HEIGHT);
        root.getChildren().add(rect);
        rectList.add(rect);
        drag.setDragedRectangle(rect);
        pipeline.addPhysicalShape(rect);
    }
    
    // system settings //
    //forced termination
    private void keySystem(KeyEvent event) {
        if (event.getCode() == KeyCode.S && event.isControlDown()) {
            System.out.println("おされてますよ〜");
            timeline.play();
        }
        else if (event.getCode() == KeyCode.E && event.isControlDown()) {
            System.out.println("押されたよ〜");
            pipeline.initTime();
            pipeline.initPosition();
            timeline.stop();
        }
        else if (event.getCode() == KeyCode.C && event.isControlDown()) {
            System.exit(0);
        }
    } 
}