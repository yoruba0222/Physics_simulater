import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.stage.Stage;

public class Collision extends Application {

          private Rectangle rect;
          private Circle circle;
          private Pane pane;
          private Line line;

          private DragE drag;

          private GJK gjk;

          @Override
          public void start(Stage stage) throws Exception {

                    try {

                              assert circle != null : "お前ら何しとんねん";

                    stage.setTitle("Collision Test");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    drag = new DragE();
                    rect = new Rectangle(400, 400, 200, 200);
                    circle = new Circle(200, 200, 100);
                    pane = new Pane();
                    line = new Line();
                    gjk = new GJK();

                    //System.out.println(circle.getCenterX()+":"+circle.getCenterY());

                    //drag.setDragedRectangle(rect);
                    drag.setDragedCircle(circle);
                    pane.getChildren().addAll(rect, circle, line);
                    Scene scene = new Scene(pane);

                    Timeline timeline = new Timeline(
                              new KeyFrame(Duration.millis(17.6), event -> changeColor(event))

                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();

                    stage.setScene(scene);
                    drag.setScene(scene);



                    stage.show();

                    } catch(Exception e) {
                              System.out.println(e);
                    }

          }

          private void changeColor(ActionEvent event) {

                    //System.out.println("おちんぽ");
                    if (gjk.getCollisionJudge(rect, circle)) {
                              rect.setFill(Color.BLUE);
                    } else rect.setFill(Color.RED);

                    Vector2 vec = gjk.getEliminateImmersveVector();
                    System.out.println(gjk.getPenetrationDepth());

                    // ここで点を求める
                    Vector2 pos, pos0, pos1;
                    double tilt = vec.y / vec.x;
                    pos0 = new Vector2(
                              circle.getCenterX() + 100,
                              tilt * (circle.getCenterX() + 100) - tilt * circle.getCenterX() + circle.getCenterY()
                    );
                    pos1 = new Vector2(
                              circle.getCenterX() - 100,
                              tilt * (circle.getCenterX() - 100) - tilt * circle.getCenterX() + circle.getCenterY()
                    );
                    if (
                              MyMath.getDistance(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2, pos0.x, pos0.y)
                              > MyMath.getDistance(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2, pos1.x, pos1.y)
                    ) pos = pos0;
                    else pos = pos1;

                    line.setStartX(circle.getCenterX());      line.setStartY(circle.getCenterY());
                    line.setEndX(pos.x);        line.setEndY(pos.y);

          }

          
}
