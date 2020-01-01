import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InertiaRect extends Application{
          ExpandedRectangle rect;
          ExpandedPane pane;
          TextField tf;
          Vector2 vec, rectCenter;

          // Physical value
          double inertia, mass = 1, angularVelocity;
          final double SHOT_MASS = 1.0, SHOT_SPEED = 1.0, SHOTED_SPEED = 0.0;

          Vector2 startPos, endPos;

          Timeline timeline;
          
          @Override
          public void start(Stage stage) throws Exception {

                    stage.setTitle("Pane test");
                    stage.setWidth(500);
                    stage.setHeight(500);

                    vec = new Vector2(0, 0);
                    startPos = new Vector2(0, 0);
                    endPos = new Vector2(0, 0);
                    pane = new ExpandedPane();
                    rect = new ExpandedRectangle(150,150, 200, 200);
                    rectCenter = new Vector2(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);
                    tf = new TextField("ここに四角形の質量を嬉しそ〜^に入力しやがれください.");
                    tf.setOnAction(event -> changeMass(tf.getText()));
                    
                    pane.getChildren().addAll(rect, tf);

                    inertia = (1.0/6.0) * mass * rect.getWidth() * rect.getWidth();
                    System.out.println(1.0/6.0);

                    Scene scene = new Scene(pane);
                    scene.setOnMousePressed(event -> getPosition(event));
                    scene.setOnMouseReleased(event ->  addPower(event));

                    stage.setScene(scene);
                    stage.show();

                    // loop processng
                    timeline = new Timeline(
                              new KeyFrame(Duration.millis(16.7), event -> update(event))
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
          }
          private void getPosition(MouseEvent event) {
                    this.startPos.x = event.getX();
                    this.startPos.y = event.getY();
          }
          private void addPower(MouseEvent event) {
                    this.endPos.x = event.getX();
                    this.endPos.y = event.getY();
                    // こいつがちょっと曲者かもしれませんね
                    Vector2 powerVec = new Vector2(this.endPos.x-this.startPos.x-rectCenter.x, this.endPos.y-this.startPos.y-rectCenter.y);
                    Vector2 radiusVec = new Vector2(this.startPos.x-rectCenter.x, this.startPos.y-rectCenter.y);
                    double rad = Math.acos((powerVec.x * radiusVec.x + powerVec.y * radiusVec.y) /
                    (MyMath.getDistance(powerVec.x, powerVec.y, 0, 0) * MyMath.getDistance(radiusVec.x, radiusVec.y, 0, 0)));

                    // 力積
                    double impulse = MyMath.getDistance(powerVec.x, powerVec.y, 0, 0)*MyMath.getDistance(radiusVec.x, radiusVec.y, 0, 0)*Math.sin(rad) ;
                    // Δωの導出
                    double deltaangularVelocity = impulse / inertia; //ここかな？

                    angularVelocity += deltaangularVelocity;
          }
          private void changeMass(String m) {
                    this.angularVelocity = 0.0;
                    double changedMass = Double.parseDouble(m);
                    mass = changedMass;
                    inertia = (1.0/6.0) * mass * rect.getWidth() * rect.getWidth();

          }
          private void update(ActionEvent event) {
                    double angulSpeed = Math.toDegrees(angularVelocity) / 60;
                    rect.setRotate(rect.getRotate() + angulSpeed);
                    //System.out.println(inertia);
          }
}
