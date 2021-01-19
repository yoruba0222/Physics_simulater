import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Test extends Application {

          Polygon tri = new Polygon(400, 10, 200, 300, 600, 0);
          @Override
          public void start(Stage stage) {
                    stage.setWidth(1000);
                    stage.setHeight(1000);

                    Pane pane = new Pane();


                    pane.getChildren().add(tri);
                    
                    Scene scene = new Scene(pane);

                    scene.setOnMousePressed(event -> mouseEvent(event));

                    stage.setScene(scene);
                    stage.show();
          }
          private void mouseEvent(MouseEvent event) {
                    System.out.println("おちんぽ");
                    if (JudgeInTriangle.getJudgeInTriangle(new Vector2(400, 10), new Vector2(200, 300), new Vector2(600, 0), new Vector2(event.getX(), event.getY()))) {
                              tri.setFill(Color.BLUE);
                    }
                    else tri.setFill(Color.RED);
          }
          
}
