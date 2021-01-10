import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Aabb extends Application{
          
          ExpandedRectangle rect;
          Line topLine, bottomLine, rightLine, leftLine;
          ExpandedPane pane;

          @Override
          public void start(Stage stage) throws Exception {
                    
                    rect = new ExpandedRectangle(200, 200, 200, 200);
                    topLine = new Line();         bottomLine = new Line();
                    rightLine = new Line();       leftLine = new Line();
                    topLine.setStroke(Color.RED);
                    bottomLine.setStroke(Color.RED);
                    rightLine.setStroke(Color.RED);
                    leftLine.setStroke(Color.RED);
                    pane = new ExpandedPane();
                    pane.getChildren().addAll(topLine, bottomLine, rightLine, leftLine, rect);
                    
                    Scene scene = new Scene(pane);
                    scene.setOnKeyPressed(event -> doKeyAction(event));

                    stage.setScene(scene);
                    stage.show();
          }
          private void doKeyAction(KeyEvent event) {
                    System.out.println("(x,y)=("+rect.getX()+","+rect.getY());
                    if (event.getCode() == KeyCode.RIGHT) {
                              rect.setRotate(rect.getRotate()+1);
                              drawAABB();
                    } else if (event.getCode() == KeyCode.LEFT) {
                              rect.setRotate(rect.getRotate()-0.5);
                              drawAABB();
                    }
          }
          private void drawAABB() {
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
                    topLine.setStartX(minX);      topLine.setStartY(maxY);
                    topLine.setEndX(maxX);      topLine.setEndY(maxY);
                    bottomLine.setStartX(minX);         bottomLine.setStartY(minY);
                    bottomLine.setEndX(maxX);           bottomLine.setEndY(minY);
                    rightLine.setStartX(maxX);      rightLine.setStartY(maxY);
                    rightLine.setEndX(maxX);      rightLine.setEndY(minY);
                    leftLine.setStartX(minX);      leftLine.setStartY(maxY);
                    leftLine.setEndX(minX);      leftLine.setEndY(minY);

                    System.out.println(rectTheta);
          }
}