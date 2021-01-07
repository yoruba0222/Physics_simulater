import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ExpandedCircle extends Circle {
    public Vector2 position = new Vector2(this.getPositionX(), this.getPositionY());
    private Vector2 centerOfGravity;
    private double mass;
    private double time = 0;
    private boolean clickedByMouse = false;

    @Override
    public String toString(){
        return "ExpandedCircle";
    }

    // constructor
    ExpandedCircle(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }

    public void update() {
        time += 0.1;

    }

    //properties
    public void initTime() {
        time = 0;
    }
    public double getPositionX() { return super.getCenterX(); }
    public double getPositionY() { return super.getCenterY(); }
    public void setPositionX(double value) {
        super.setCenterX(value);
        this.position.x = value;
    }
    public void setPositionY(double value) {
        super.setCenterY(value);
        this.position.y = value;
    }
    //@Override
    //public void setCenterY()


    public void fall() {

    }
}