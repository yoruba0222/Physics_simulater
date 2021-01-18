public class Vector2 {
    double x;
    double y;

    public Vector2() {}

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void init(double x, double y) {
              this.x = x;
              this.y = y;
    }

    public Vector2 inverse() {
              return new Vector2((-1)*this.x, (-1)*this.y);
    }
}
