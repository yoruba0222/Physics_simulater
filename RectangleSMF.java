import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RectangleSMF extends Application {
          
          ExpandedRectangle rect;
          ExpandedCircle circle;
          double[] rectX = new double[4];
          double[] rectY = new double[4];
          double startX, startY, endX, endY;
          Pane root;
          Vector2 vec;

          @Override
          public void start(Stage stage) throws Exception  {
                    stage.setTitle("test");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    rect = new ExpandedRectangle(200, 200, 200, 200);
                    circle = new ExpandedCircle(-100, -100, 10);
                    vec = new Vector2(0, 0);
                    root = new Pane();
                    root.getChildren().addAll(rect, circle);

                    Scene scene = new Scene(root);
                    //scene.setOnMousePressed(event -> getVerticesPosition(event));
                    scene.setOnMousePressed(event -> getStartPosition(event));
                    scene.setOnMouseReleased(event -> setVector(event));
                    scene.setOnKeyPressed(event -> doKeyAction(event));

                    stage.setScene(scene);
                    stage.show();

          }
          private void getVerticesPosition() {
                    double x_0, y_0;
                    x_0 = rect.getX() + (rect.getWidth())/2;
                    y_0 = rect.getY() + (rect.getHeight())/2;

                    System.out.println(x_0 + ";" + y_0);
                    
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
          }
          private void getStartPosition(MouseEvent event) {
                    this.startX = event.getX();
                    this.startY = event.getY();

                    getVerticesPosition();
          }
          // ベクトルも引数に入れるかもしれません
          private void setVector(MouseEvent event) {
                    
                    this.endX = event.getX();
                    this.endY = event.getY();
                    
                    //if (this.startX > this.endX) {
                    //          double tmp = this.startX;
                    //          this.startX = this.endX;
                    //          this.endX = tmp;
                    //          tmp = this.startY;
                    //          this.startY = this.endY;
                    //          this.endY = tmp;
                    //}

                    this.vec.x = this.endX - this.startX;
                    this.vec.y = this.endY - this.startY;

                    System.out.println(this.vec.x +":"+ this.vec.y);

                    // 問題の部分
                    CalculateSMF(vec);

          }
          private void doKeyAction(KeyEvent event) {
                    System.out.println("(x,y)=("+rect.getX()+","+rect.getY());
                    if (event.getCode() == KeyCode.RIGHT) {
                              rect.setRotate(rect.getRotate()+0.5);
                    } else if (event.getCode() == KeyCode.LEFT) {
                              rect.setRotate(rect.getRotate()-0.5);
                    }
          }
          
          public void CalculateSMF(Vector2 vec) {

                    // calculate bounding sphere information
                    Vector2 centerPos = new Vector2((rectX[0]+rectX[2])/2, (rectY[0]+rectY[2]/2));
                    double radius = MyMath.getDistance(rectX[0], rectY[0], centerPos.x, centerPos.y);
                    Vector2 normalizedVec = MyMath.normalizationVector(vec);
                    Vector2 smfPos = new Vector2(centerPos.x+radius*normalizedVec.x,
                                                                      centerPos.y+radius*normalizedVec.y);
                    
                    double[] smfDistance = new double[4];

                    int leastIndex = 0;
                    for (int i=0;i<4;i++) {
                              smfDistance[i] = MyMath.getDistance(smfPos.x, smfPos.y, rectX[i], rectY[i]);
                              if(smfDistance[leastIndex] > smfDistance[i]) {
                                        leastIndex = i;
                              }
                    }

                    System.out.println(centerPos.x+":"+centerPos.y);

                    circle.setPositionX(rectX[leastIndex]);
                    circle.setPositionY(rectY[leastIndex]);
          }
}