import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
public class SMF {
          public Vector2 getSupportMapping(Shape shape, Vector2 d) {
                    String type = shape.toString();
                    
                    if (type.contains("Rectangle")) {
                              Rectangle rect = (Rectangle)shape;

                              double x_0, y_0;
                              x_0 = rect.getX() + 100;
                              y_0 = rect.getY() + 100;
                              double[] rectX = new double[4];
                              double[] rectY = new double[4];
                              double rectTheta =  (-1)*rect.getRotate();
                              double length = 100 * Math.sqrt(2);
                              rectX[0] = length * Math.cos(Math.toRadians(-45+rectTheta)) + x_0;
                              rectY[0] = length * Math.sin(Math.toRadians(-45+rectTheta)) + y_0; 
                              rectX[1] = length * Math.cos(Math.toRadians(-135+rectTheta)) + x_0;
                              rectY[1] = length * Math.sin(Math.toRadians(-135+rectTheta)) + y_0;
                              rectX[2] = length * Math.cos(Math.toRadians(-225+rectTheta)) + x_0;
                              rectY[2] = length * Math.sin(Math.toRadians(-225+rectTheta)) + y_0;
                              rectX[3] = length * Math.cos(Math.toRadians(-315+rectTheta)) + x_0;
                              rectY[3] = length * Math.sin(Math.toRadians(-315+rectTheta)) + y_0;
                              double maxX, minX, maxY, minY;
                              Arrays.sort(rectX); Arrays.sort(rectY);
                              maxX = rectX[3];    maxY = rectY[3];
                              minX = rectX[0];    minY = rectY[0];
                    }
                    else if (type.contains("Circle")) {}
                    else if (type.contains("Polygon")) {}
                    return new Vector2(0, 0);
          }
}
