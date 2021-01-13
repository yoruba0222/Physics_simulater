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
          Vector2 vec;

          // Physical value
          double inertia, mass, angularVelocity,changedSpeed, nowSpeed;
          final double SHOT_MASS = 1.0, SHOT_SPEED = 1.0, SHOTED_SPEED = 0.0;

          Vector2 startPos, endPos;

          Timeline timeline;
          
          @Override
          public void start(Stage stage) throws Exception {

                    stage.setTitle("Pane test");
                    stage.setWidth(1000);
                    stage.setHeight(800);

                    vec = new Vector2(0, 0);
                    startPos = new Vector2(0, 0);
                    endPos = new Vector2(0, 0);
                    pane = new ExpandedPane();
                    rect = new ExpandedRectangle(200, 200, 200, 200);
                    tf = new TextField("ここに四角形の質量を嬉しそ〜^に入力しやがれください.");
                    tf.setOnAction(event -> changeMass(tf.getText()));

                    pane.setPosition(rect, 100, 100);
                    
                    pane.getChildren().add(rect);

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

          }
          private void changeMass(String m) {
                    this.angularVelocity = 0.0;
                    double changedMass = Double.parseDouble(m);
          }
          private void addPower(MouseEvent event) {}
          private void update(ActionEvent event) {
                    double angulSpeed = Math.toDegrees(angularVelocity) / 60;
                    rect.setRotate(rect.getRotate() + angulSpeed);
          }
}
