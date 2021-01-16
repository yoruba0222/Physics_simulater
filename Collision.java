import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Collision extends Application {

          private Rectangle rect;
          private Polygon triangle;
          private Pane pane;

          private DragE drag;

          @Override
          public void start(Stage stage) throws Exception {

                    stage.setTitle("Collision Test");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    drag = new DragE();
                    rect = new Rectangle(400, 400, 200, 200);
                    triangle = new Polygon(0, -50*Math.sqrt(3), -100, 50*Math.sqrt(3), 100, 50*Math.sqrt(3));
                    triangle.setLayoutX(100);
                    triangle.setLayoutY(100);
                    pane = new Pane();

                    System.out.println(triangle.getLayoutX()+":"+triangle.getLayoutY());

                    //drag.setDragedRectangle(rect);
                    drag.setDragedPolygon(triangle);
                    pane.getChildren().addAll(rect, triangle);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    drag.setScene(scene);

                    stage.show();
          }


          // GJK Algorythm
          private void gjk() {
                    Vector2 rectCenter = new Vector2(0, 0);
          }
}
