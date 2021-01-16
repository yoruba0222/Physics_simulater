package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CollisionFloor extends Application {
    //オブジェクト
    Circle circle;
    Rectangle rect;
    
    //変数
    double time = 0;
    final double GRAVITY = 9.8;
    final double CIRCLE_MASS = 5;
    boolean floorCollision = false;
    double circleSpeed = 1;
    double circleSpeedNow;

    //マジックナンバー
    final int FLOORWIDTH =  1600;
    final int FLOORHEIGHT = 100;
    
    //startメソッド
    @Override
    public void start(Stage stage) throws Exception {
        //ステージの大きさを定義する
        stage.setTitle("椎名林檎");
        stage.setWidth(1600);
        stage.setHeight(1000);

        //変数の初期化
        circle = new Circle(700, 100, 50);
        circle.setFill(Color.PINK);
        rect = new Rectangle(000, 750, FLOORWIDTH, FLOORHEIGHT);
        Pane root = new Pane();
        root.getChildren().addAll(circle, rect);
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(event -> clickedMouse(event));
        stage.setScene(scene);
        stage.show();

        //落下運動
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(17), event -> fallAction(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    //落下イベント
    void fallAction(ActionEvent event) {
        time += 0.1;
        
        //衝突判定.
        if (MyMath.getDistance(circle.getCenterX(), 0.5  * GRAVITY * (time + 0.1) * (time + 0.1), circle.getCenterX(), rect.getY()) < 50
                || circleSpeed < 0) {
            circle.setCenterY(699);
            time = 0;
            floorCollision = true;
        } else floorCollision = false;

        //非衝突時の処理
        if (floorCollision != true){
            double y = 0.5 * GRAVITY * time * time;
            circleSpeed = time * GRAVITY;
            circleSpeedNow = circleSpeed;
            circle.setCenterY(y);

        //衝突時の処理
        } else {
            circleSpeed = circleSpeedNow + time * (-1) * GRAVITY;
            circle.setCenterY(circleSpeedNow * time + 0.5 * (-1) * GRAVITY * time * time);
        }

        System.out.println(MyMath.getDistance(circle.getCenterX(), circle.getCenterY(), circle.getCenterX(), rect.getY()));
    }

    //座標を取得
    void clickedMouse(MouseEvent event) {
        System.out.println(circle.getCenterY());
    }
}