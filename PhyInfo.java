import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
  ポリトープの情報を保持しておく構造体のようなクラス
*/

public class PhyInfo {

          private String type;

          private Circle circle;
          private Rectangle rect;

          private double inertia;
          private double mass;
          private Vector2 speed;
          private double angular;

          private Vector2 centerPos;

          private Vector2 initPos;

          public PhyInfo(Shape shape) {

                    initPos = new Vector2(200, 200);

                    if (shape.toString().contains("Rectangle")) {
                              type = "Rectangle";
                              rect = (Rectangle) shape;

                              centerPos = new Vector2(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                    }
                    else if (shape.toString().contains("Circle")) {
                              type = "Circle";
                              circle = (Circle) shape;

                              centerPos = new Vector2(circle.getCenterX(), circle.getCenterY());
                    }

                    mass = 10;
                    speed = new Vector2(0, 0);
                    angular = 0;
                    this.setInertia();

                    if(type.equals("Rectangle")) speed = new Vector2(0, -100);
          }

          public double getMass() { return this.mass; }
          public double getInertia() { return this.inertia; }
          public Vector2 getSpeed() {return this.speed; }
          public double getAngular() { return this.angular; }
          public Vector2 getInitPos() { return this.initPos; }
          public Vector2 getCenterPos() { return this.centerPos; }

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
                    
                    if (type.equals("Rectangle")) {
                              this.inertia = (1.0/12.0) * this.mass * (Math.pow(rect.getWidth(), 2)+Math.pow(rect.getHeight(), 2));
                              System.out.println(rect.getWidth());
                    }

                    else if (type.equals("Circle")) this.inertia = (1.0/2.0) * this.mass * Math.pow(circle.getRadius(), 2);

                    

          }
          public void setInitPos() {
                    if (type.equals("Circle"))
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

          public void init() {
                    mass = 10;
                    speed = new Vector2(0, 0);
                    angular = 0;
                    this.setInertia();
          }
}
