import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class test extends Application {
          @Override
          public void start(Stage stage) {
                    Shape shape;
                    Rectangle rect = new Rectangle();
                    Circle circle = new Circle();
                    Polygon polygon = new Polygon();
                    shape = rect;
                    System.out.println(shape.toString());
                    System.out.println(circle.toString());
                    System.out.println(polygon.toString());
          }
          
}
