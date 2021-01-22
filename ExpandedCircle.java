//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ExpandedCircle extends Circle {
    
          // 物理的値
          private double mass;
          private double inertia;
          private Vector2 speed;
          private double angular;

    @Override
    public String toString(){
        return "ExpandedCircle";
    }

          // constructor
          public ExpandedCircle() {
                    this.mass = 10;
                    this.speed = 0;
                    this.angular = 0;
                    this.inertia = 
                    super();
          }
          public ExpandedCircle(double centerX, double centerY, double radius) {
                   this.mass = 10;
                   this.speed = 0;
                   this.angular = 0;
                   this.inertia = 
                    super(centerX, centerY, radius);
          }
    //@Override
    //public void setCenterY()

          public double getInertia() {
                    
          }
}