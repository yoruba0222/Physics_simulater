import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
  ポリトープの情報を保持しておく構造体のようなクラス
*/

public class PhyInfo {

          public String type;

          private Circle circle;
          private Rectangle rect;

          public double inertia;
          public double mass;
          public Vector2 speed;
          public double angular;

          public PhyInfo(Shape shape) {

                    if (shape.toString().contains("Rectangle")) {
                              type = "Rectangle";
                              rect = (Rectangle) shape;
                    }
                    else if (shape.toString().contains("Circle")) {
                              type = "Circle";
                              circle = (Circle) shape;

                    mass = 10;
                    speed = new Vector2(0, 0);
                    angular = 0;
                    this.setInertia();
                    }
          }

          public void setInertia() {
                    
                    if (type.equals("Rectangle")) this.inertia = (1/12) * this.mass * (Math.pow(rect.getWidth(), 2)+Math.pow(rect.getHeight(), 2));

                    else if (type.equals("Circle")) this.inertia = (1/2) * this.mass * Math.pow(circle.getRadius(), 2);

          }
}
