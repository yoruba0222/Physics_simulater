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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LimitedSystemVersion extends Application {

    //object
    private Timeline timeline;
    private Pane root;
    private PhysicsPipeline pipeline;
    private ArrayList<ExpandedCircle> circleList;
    private ArrayList<ExpandedRectangle> rectList;
    private ArrayList<Boolean> circle_Clicked_Frag, rect_Clicked_Frag;

    final private double INIT_RADIUS = 50;
    final private double INIT_CENTER_X = 100;
    final private double INIT_CENTER_Y = 100;
    final private double INIT_WIDTH = 100;
    final private double INIT_HEIGHT = 100;

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

        root = new Pane();
        pipeline = new PhysicsPipeline();
        circleList = new ArrayList<>();
        rectList = new ArrayList<>();
        circle_Clicked_Frag = new ArrayList<>();
        rect_Clicked_Frag = new ArrayList<>();

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
        scene.setOnMouseClicked(event -> moveObject(event));
        scene.setOnMouseDragged(event -> moveObject(event));
        scene.setOnMouseReleased((event -> releaseObject(event)));
        //scene.setOnKeyPressed(event -> stopSimulation(event));
        //scene.setOnKeyPressed(event -> playSimulation(event));
        scene.setOnKeyPressed(event -> keySystem(event));
        //scene.setOnKeyPressed(event -> playSimulation(event));

        stage.setScene(scene);
        stage.show();

        //loop processing
        timeline = new Timeline(
            new KeyFrame(Duration.millis(16.7), event -> pipeline.update(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    // /* event functions */ //

    // menu functions //
    private void generateCircle() {
        ExpandedCircle circle = new ExpandedCircle(INIT_CENTER_X, INIT_CENTER_Y, INIT_RADIUS);
        root.getChildren().add(circle);
        circleList.add(circle);
        circle_Clicked_Frag.add(false);
        pipeline.addPhysicalShape(circle);
    }
    private void generateRectangle() {
        ExpandedRectangle rect = new ExpandedRectangle(INIT_CENTER_X, INIT_CENTER_Y, INIT_WIDTH, INIT_HEIGHT);
        root.getChildren().add(rect);
        rectList.add(rect);
        rect_Clicked_Frag.add(false);
        pipeline.addPhysicalShape(rect);
    }

    // drag functions
    void releaseObject(MouseEvent event) {
        for(int i = 0;i < circle_Clicked_Frag.size();i++) {
            if (event.getButton() == MouseButton.PRIMARY && circle_Clicked_Frag.get(i) == true) {
                circle_Clicked_Frag.set(i, false);
                return;
            }
        }
        for(int i = 0;i < rect_Clicked_Frag.size();i++) {
            if (event.getButton() == MouseButton.PRIMARY && rect_Clicked_Frag.get(i) == true) {
                rect_Clicked_Frag.set(i, false);
                return;
            }
        }
    }
    void moveObject(MouseEvent event) {
        //rectangle processings
        for(int i=0;i < rectList.size(); i++) {
            double rectX = rectList.get(i).getPositionX();
            double rectY = rectList.get(i).getPositionY();
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double distance = Math.sqrt(Math.pow(rectX - mouseX, 2)
                    + Math.pow(rectY - mouseY, 2));
            System.out.println(distance);
            if(distance <= 50)rect_Clicked_Frag.set(i, true);
            else rect_Clicked_Frag.set(i, false);
            System.out.println(event.getX() + ":" + event.getY());
            //}
            /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                    rect_Clicked_Frag.get(i) == true && rectList.get(i).toString()
                    == "ExpandedRectangle") {
                rectList.get(i).setX(event.getSceneX());
                rectList.get(i).setY(event.getSceneY());
                rectList.get(i).position.x = event.getSceneX();
                rectList.get(i).position.y = event.getSceneY();
                return;
            }
        }
        //circle processings
        for(int i=0;i < circleList.size();i++) {
            double circleX = circleList.get(i).getPositionX();
            double circleY = circleList.get(i).getPositionY();
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double distance = Math.sqrt(Math.pow(circleX - mouseX, 2)
                    + Math.pow(circleY - mouseY, 2));
            System.out.println(distance);
            if(distance <= 50)circle_Clicked_Frag.set(i, true);
            else circle_Clicked_Frag.set(i, false);
            System.out.println(event.getX() + ":" + event.getY());
            //}
            /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                    circle_Clicked_Frag.get(i) == true && circleList.get(i).toString()
                    == "ExpandedCircle") {
                circleList.get(i).setCenterX(event.getSceneX());
                circleList.get(i).setCenterY(event.getSceneY());
                circleList.get(i).position.x = event.getSceneX();
                circleList.get(i).position.y = event.getSceneY();
                return;
            }
        }
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