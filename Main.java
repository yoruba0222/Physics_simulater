/*
//
// 12月22日に提出予定の機能限定版のソースコード
//
*/

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    //object
    private Timeline timeline;
    private DragE drag;
    private Pane root;
    private PhysicsPipeline pipeline;
    private ArrayList<Circle> circleList;
    private ArrayList<Rectangle> rectList;

    private TextField speedField, angularField, sizeField, massFIeld, eField;
    private Label speedLabel, angularLabel, sizeLabel, massLabel, eLabel;

    private HashMap<Shape, PhyInfo> rigidBodys = new HashMap<>();

    final private double INIT_RADIUS = 50;
    final private double INIT_CENTER_X = 100;
    final private double INIT_CENTER_Y = 100;
    final private double INIT_WIDTH = 100;
    final private double INIT_HEIGHT = 100;

    //field variable
    final private int FLOORWIDTH = 1400;    //the width of the stage
    final private int FLOORHEIGHT = 900;   //the height of the stage

    // Objects detected by AABB
    //private ArrayList<Shape> processShapes = new ArrayList<>();

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
        drag = new DragE();
        pipeline = new PhysicsPipeline(rigidBodys);
        circleList = new ArrayList<>();
        rectList = new ArrayList<>();

        //menu settings
        MenuBar menuBar = new MenuBar();
        Menu generateMenu = new Menu("Generate");
        MenuItem menuCircle = new MenuItem("Circle");
        MenuItem menuRectangle = new MenuItem("Rectangle");
        MenuItem menuEllipse = new MenuItem("Ellipse");

        // text field settings
        eLabel = new Label("Coefficient of restitution:");
        eField = new TextField ();
        eLabel.setLayoutX(100);
        eField.setLayoutX(250);


        // text field's even functions settings
        eField.setOnAction(event -> setCoefficientOfRestitution(eField.getText()));

        generateMenu.getItems().addAll(menuCircle,menuEllipse,menuRectangle);
        menuBar.getMenus().addAll(generateMenu);
        root.getChildren().add(menuBar);

        root.getChildren().addAll(eLabel, eField);

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
            new KeyFrame(Duration.millis(10), event -> pipeline.update(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    // /* event functions */ //

    // menu functions //
    private void generateCircle() {
        Circle circle = new Circle(INIT_CENTER_X, INIT_CENTER_Y, INIT_RADIUS);
        root.getChildren().add(circle);
        circleList.add(circle);
        drag.setDragedCircle(circle);
        pipeline.addPhysicalShape(circle);
        PhyInfo info = new PhyInfo(circle);
        rigidBodys.put(circle, info);
    }
    private void generateRectangle() {
        Rectangle rect = new Rectangle(INIT_CENTER_X, INIT_CENTER_Y, INIT_WIDTH, INIT_HEIGHT);
        root.getChildren().add(rect);
        rectList.add(rect);
        drag.setDragedRectangle(rect);
        pipeline.addPhysicalShape(rect);
        PhyInfo info = new PhyInfo(rect);
        rigidBodys.put(rect, info);
    }

    // text field //
    //  set Coefficient Of Restitution
    private void  setCoefficientOfRestitution(String text) {
        double value = Double.parseDouble(text);
        pipeline.setCoefficientOfRestitution(value);
    }

    // system settings //
    //forced termination
    private void keySystem(KeyEvent event) {
        if (event.getCode() == KeyCode.S && event.isControlDown()) {
            System.out.println("おされてますよ〜");

            // ストップされた時に物体の位置を元に戻すため
            for (Rectangle rect : rectList) {
                      rigidBodys.get(rect).setInitPos();
            }
            for (Circle circle : circleList) {
                      rigidBodys.get(circle).setInitPos();
            }

            timeline.play();
        }
        else if (event.getCode() == KeyCode.E && event.isControlDown()) {
            System.out.println("押されたよ〜");
            pipeline.initPosition();
            pipeline.init();
            timeline.stop();
        }
        else if (event.getCode() == KeyCode.C && event.isControlDown()) {
            System.exit(0);
        }
    } 
}