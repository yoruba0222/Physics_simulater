import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Collision extends Application {

          private Rectangle rect;
          private Circle circle;
          private Pane pane;

          private DragE drag;

          @Override
          public void start(Stage stage) throws Exception {

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

                    scene.setOnMouseDragged(event -> changeColor(event));

                    stage.setScene(scene);
                    drag.setScene(scene);

                    stage.show();
          }
          private void changeColor(MouseEvent event) {
                    System.out.println("おちんぽ");
                    if (GJK.getCollisionJudge(rect, circle)) {
                              rect.setFill(Color.BLUE);
                    } else rect.setFill(Color.RED);
          }
}
