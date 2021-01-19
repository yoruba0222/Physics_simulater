import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Collision extends Application {

          private Rectangle rect;
          private Circle circle;
          private Pane pane;

          private DragE drag;

          @Override
          public void start(Stage stage) throws Exception {

                    try {

                              assert circle != null : "お前ら何しとんねん";

                    stage.setTitle("Collision Test");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    drag = new DragE();
                    rect = new Rectangle(400, 400, 200, 200);
                    circle = new Circle(200, 200, 100);
                    pane = new Pane();

                    //System.out.println(circle.getCenterX()+":"+circle.getCenterY());

                    //drag.setDragedRectangle(rect);
                    drag.setDragedCircle(circle);
                    pane.getChildren().addAll(rect, circle);
                    Scene scene = new Scene(pane);

                    Timeline timeline = new Timeline(
                              new KeyFrame(Duration.millis(17.6), event -> changeColor(event))

                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();

                    stage.setScene(scene);
                    drag.setScene(scene);



                    stage.show();

                    } catch(Exception e) {
                              System.out.println(e);
                    }

          }

          private void changeColor(ActionEvent event) {


                    try {

                    //System.out.println("おちんぽ");
                    if (GJK.getCollisionJudge(rect, circle)) {
                              rect.setFill(Color.BLUE);
                    } else rect.setFill(Color.RED);

          } catch(Exception e) {
                    System.out.println(e);
          }
          }

          
}
