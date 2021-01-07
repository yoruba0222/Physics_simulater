import javafx.scene.shape.Rectangle;

public class ExpandedRectangle extends Rectangle {
    public Vector2 position = new Vector2(this.getPositionX(), this.getPositionY());
    private Vector2 centerOfGravity;
    private double mass;

    @Override
    public String toString() {
        return "ExpandedRectangle";
    }

    ExpandedRectangle(double centerX, double centerY, double width, double height) {
        super(centerX, centerY, width, height);
    }

    //properties
    public double getPositionX() {
        return this.getX() + getScaleX()/2;
    }
    public double getPositionY() {
        return this.getY() + getScaleY()/2;
    }
    public void setPositionX(double value) {
          super.setX(value);
          this.position.x = value;
      }
      public void setPositionY(double value) {
          super.setY(value);
          this.position.y = value;
      }
}