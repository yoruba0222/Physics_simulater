import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawLine extends Application{
          
          double startX, startY, endX, endY;

          ArrayList<Line> lineList;

          Line tmpLine = new Line(0, 0, 0, 0);
          ExpandedCircle originPoint;


          ExpandedPane pane;

          @Override
          public void start(Stage stage) throws Exception {
                    
                    stage.setTitle("draw line");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    pane = new ExpandedPane();
                    lineList = new ArrayList<>();
                    originPoint = new ExpandedCircle(200, 200, 10);
                    originPoint.setFill(Color.PINK);
                    
                    pane.getChildren().addAll(tmpLine, originPoint);

                    Scene scene = new Scene(pane);
                    scene.setOnMousePressed(event -> setStartPosition(event));
                    scene.setOnMouseDragged(event -> setTemporaryLine(event));
                    scene.setOnMouseReleased(event-> setLine(event));

                    stage.setScene(scene);
                    stage.show();
          }

          private void setStartPosition(MouseEvent event) {
                    this.startX = event.getX();
                    this.startY = event.getY();
          }
          private void setTemporaryLine(MouseEvent event) {
                    tmpLine.setStartX(this.startX);
                    tmpLine.setStartY(this.startY);
                    tmpLine.setEndX(event.getX());
                    tmpLine.setEndY(event.getY());
                    tmpLine.setFill(Color.AQUA);
          }
          private void setLine(MouseEvent event) {
                    this.endX = event.getX();
                    this.endY = event.getY();
                    Line line = new Line(this.startX, this.startY, this.endX, this.endY);
                    pane.getChildren().add(line);
                    tmpLine.setStartX(0);
                    tmpLine.setStartY(0);
                    tmpLine.setEndX(0);
                    tmpLine.setEndY(0);

                    //startXの方がendYよりも常に小さいようにする.
                    if (this.startX > this.endX) {
                              double tmp = this.startX;
                              this.startX = this.endX;
                              this.endX = tmp;
                              tmp = this.startY;
                              this.startY = this.endY;
                              this.endY = tmp;
                    }

                    //計算フェーズ
                    double m = (this.endY - this.startY) / (this.endX - this.startX);
                    double crossX = (200.0+200.0*m+m*m*this.startX-m*this.startY) / (1+m*m);
                    double crossY = m*crossX-m*this.startX+this.startY;
                    double leastDis = MyMath.getDistance(crossX, crossY, originPoint.getCenterX(), originPoint.getCenterY());
                    double startDis = MyMath.getDistance(this.startX, this.startY, originPoint.getCenterX(), originPoint.getCenterY());
                    double endDis = MyMath.getDistance(this.endX, this.endY, originPoint.getCenterX(), originPoint.getCenterY());
                    
                    //最短距離描画フェーズ
                    Line leastLine = new Line();
                    leastLine.setEndX(originPoint.getCenterX());
                    leastLine.setEndY(originPoint.getCenterY());
                    if  (crossX > this.startX && crossX < this.endX) {
                              leastLine.setStartX(crossX);
                              leastLine.setStartY(crossY);
                              System.out.println(originPoint.getCenterX());
                    }
                    //ここからはうまく機能しているみたいですね
                    else if (startDis < endDis) {
                              leastLine.setStartX(this.startX);
                              leastLine.setStartY(this.startY);
                    }
                    else if(endDis < startDis) {
                              leastLine.setStartX(this.endX);
                              leastLine.setStartY(this.endY);
                    }
                    leastLine.setStroke(Color.RED);
                    pane.getChildren().add(leastLine);
          }
}
