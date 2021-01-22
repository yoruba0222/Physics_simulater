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

          public Vector2 initPos;

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

          public double getMass() { return this.mass; }
          public double getInertia() { return this.inertia; }
          public Vector2 getSpeed() {return this.speed; }
          public double getAngular() { return this.angular; }
          public Vector2 getInitPos() { return this.initPos; }

          public void setMass(double tmp) {
                    this.mass = tmp;
                    this.setInertia();
          }
          public void setSpeed(Vector2 tmp) {
                    this.speed = tmp;
          }
          public void setAngular(double tmp) {
                    this.angular = tmp;
          }
          public void setInertia() {
                    
                    if (type.equals("Rectangle")) this.inertia = (1/12) * this.mass * (Math.pow(rect.getWidth(), 2)+Math.pow(rect.getHeight(), 2));

                    else if (type.equals("Circle")) this.inertia = (1/2) * this.mass * Math.pow(circle.getRadius(), 2);

          }
          public void setInitPos() {
                    if (type.equals("circle"))
                    this.initPos = new Vector2(
                              circle.getCenterX(),
                              circle.getCenterY()
                    );
                    else if (type.equals("Rectangle")) 
                    this.initPos = new Vector2(
                              rect.getX(),
                              rect.getY()
                    );
          }
}
