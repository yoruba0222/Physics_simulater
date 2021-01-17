import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.Arrays;
public class SMF {
          public static Vector2 getSupportMapping(Shape shape, Vector2 d) {

                    String type = shape.toString();
                    Vector2 smPosition = new Vector2(0, 0);
                    
                    if (type.contains("Rectangle")) {

                              Rectangle rect = (Rectangle) shape;

                              Vector2 rectCenterPos = new Vector2(rect.getX() + 100, rect.getY() + 100);
                              Vector2[] vertex = new Vector2[4];
                              double rectTheta =  (-1)*rect.getRotate();
                              double length = 100 * Math.sqrt(2);
                              vertex[0] = new Vector2(
                                        length * Math.cos(Math.toRadians(-45+rectTheta)) + rectCenterPos.x,
                                        length * Math.sin(Math.toRadians(-45+rectTheta)) + rectCenterPos.y
                              );
                              vertex[1] = new Vector2 (
                                        length * Math.cos(Math.toRadians(-135+rectTheta)) + rectCenterPos.x,
                                        length * Math.sin(Math.toRadians(-135+rectTheta)) + rectCenterPos.y
                              );
                              vertex[2] = new Vector2 (
                                        length * Math.cos(Math.toRadians(-225+rectTheta)) + rectCenterPos.x,
                                        length * Math.sin(Math.toRadians(-225+rectTheta)) + rectCenterPos.y
                              );
                              vertex[3] = new Vector2(
                                        length * Math.cos(Math.toRadians(-315+rectTheta)) + rectCenterPos.x,
                                        length * Math.sin(Math.toRadians(-315+rectTheta)) + rectCenterPos.y
                              );

                              smPosition.x = rectCenterPos.x + MyMath.getDistance(
                                        rectCenterPos.x, rectCenterPos.y, 
                                        rect.getX(), rect.getY()) * (d.x/MyMath.getNorm(d));
                              smPosition.y = rectCenterPos.y + MyMath.getDistance(
                                        rectCenterPos.x, rectCenterPos.y, 
                                        rect.getX(), rect.getY()) * (d.y/MyMath.getNorm(d));

                              double[] distance =new double[4];
                              for (int i=0; i<distance.length; i++){
                                        distance[i] = MyMath.getDistance(smPosition.x, smPosition.y, vertex[i].x, vertex[i].y);
                              }

                              //for (int i=0; i<4; i++) {
                              //          System.out.println(distance[i]);
                              //}

                              int minIndexValue = minIndex(distance);

                              smPosition.init(vertex[minIndexValue].x, vertex[minIndexValue].y);

                              
                    }
                    else if (type.contains("Circle")) {

                              Circle circle = (Circle) shape;

                              Vector2 circleCenter = new Vector2(circle.getCenterX(), circle.getCenterY());
                              smPosition.init(
                                        circleCenter.x + circle.getRadius() * (d.x/MyMath.getNorm(d)),
                                        circleCenter.y + circle.getRadius() * (d.y/MyMath.getNorm(d))
                              );

                    }
                    else if (type.contains("Polygon")) {

                              Polygon polygon = (Polygon) shape;

                              Vector2[] vertex = new Vector2[6];
                              for (int i=0; i<3; i++) {
                                        vertex[i] = new Vector2(polygon.getPoints().get(i), polygon.getPoints().get(2*i+1));
                                        System.out.println(vertex[i].x+":"+vertex[i].y);
                              }
                              System.out.println(polygon.toString());
                              Vector2 circleCenter = new Vector2((vertex[0].x+vertex[1].x)/2, (vertex[0].y+vertex[1].y)/2);
                              double radius = MyMath.getDistance(circleCenter.x, circleCenter.y, vertex[2].x, vertex[2].y);

                              smPosition.init(
                                        circleCenter.x + radius * (d.x/MyMath.getNorm(d)),
                                        circleCenter.y + radius * (d.y/MyMath.getNorm(d))
                              );

                              double[] distance = new double[3];
                              for (int i=0; i<distance.length; i++) {
                                        distance[i] = MyMath.getDistance(smPosition.x, smPosition.y, vertex[i].x, vertex[i].y);
                              }

                              //or (int i=0; i<3; i++)System.out.println(distance[i]);

                              int minIndexValue = minIndex(distance);

                              smPosition.init(vertex[minIndexValue].x, vertex[minIndexValue].y);
                              
                    }

                    

                    return smPosition;
          }

          public static int minIndex(double[] list) {
                    double[] listClone = list.clone();
                    Arrays.sort(list);
                    for (int i=0; i<list.length; i++) {
                              if (listClone[i] == list[0]) return i;
                    }
                    return 0;
          }
}
