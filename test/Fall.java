package test;

/*
/ 　球体の自由落下シミュレーション
*/


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Fall extends Application {
    //オブジェクト
    Circle circle;
    
    //変数
    double time = 0;
    final double GRAVITY = 9.8;
    boolean floorCollision = false;

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
        Pane root = new Pane();
        root.getChildren().addAll(circle);
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(event -> clickedMouse(event));
        stage.setScene(scene);
        stage.show();

        //落下運動
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(16.7), event -> fallAction(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    //落下イベント
    void fallAction(ActionEvent event) {
        time+=0.1;
        double y = 0.5 * GRAVITY * time * time;
        circle.setCenterY(y);
    }

    //座標を取得
    void clickedMouse(MouseEvent event) {
        System.out.println("X座標:" + event.getX() + "\nY座標:" + event.getY());
    }
}