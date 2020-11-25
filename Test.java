package Samples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {
    //オブジェクト
    Circle circle;
    Rectangle rect;
    
    //変数
    double time = 0;
    final double GRAVITY = 9.8;
    boolean floorCollision = false;
    int mouse_Click_Flag = 0;

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

        //イベントハンドラ
        scene.setOnMouseClicked(event -> moveObject(event));
        scene.setOnMouseDragged(event -> moveObject(event));
        scene.setOnMouseReleased(event -> releaseObject(event));

        stage.setScene(scene);
        stage.show();

        //落下運動
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(17), event -> printCoor(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void releaseObject(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && mouse_Click_Flag == 1) {
            mouse_Click_Flag = 0;
        }
    }
    void moveObject(MouseEvent event) {
        //if(event.getEventType() == MouseEvent.MOUSE_PRESSED /*&&
        //    event.getButton() == MouseButton.PRIMARY*/) {
            double rectX = circle.getCenterX();
            double rectY = circle.getCenterY();
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double distance = Math.sqrt(Math.pow(rectX - mouseX, 2) + Math.pow(rectY - mouseY, 2));
            if(distance <= 50)mouse_Click_Flag = 1;
            else mouse_Click_Flag = 0;
            System.out.println(event.getX() + ":" + event.getY());
        //}
        /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
            mouse_Click_Flag == 1) {
                circle.setCenterX(event.getSceneX());
                circle.setCenterY(event.getSceneY());
        }
    }

    //座標を取得
    void printCoor(ActionEvent event) {
        System.out.println(circle.getCenterY());
    }
}