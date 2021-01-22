import javafx.scene.shape.Rectangle;

public class ExpandedRectangle extends Rectangle {

    public Vector2 position = new Vector2(this.getPositionX(), this.getPositionY());

    // 物理的値
    private double mass;
    private double inertia;
    private Vector2 speed;
    private double angular;

    @Override
    public String toString() {
        return "ExpandedRectangle";
    }

    ExpandedRectangle(double centerX, double centerY, double width, double height) {
        super(centerX, centerY, width, height);
        //this.centerOfGravity
        this.mass = 1.0; //[kg]
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
      public void setSide(double value) {
                super.setWidth(value);
                this.setInertia();
      }
      public void setVartival(double value) {
                super.setHeight(value);
                this.setInertia();
      }
      public void setMass(double value) {
                this.mass = value;
                this.setInertia();
      }
      public void setInertia() {
                this.inertia = (1/12) * this.mass * (Math.pow(super.getWidth(), 2) + Math.pow(super.getHeight(), 2));
      }
      public double getInertia() {
                return this.inertia;
      }
}