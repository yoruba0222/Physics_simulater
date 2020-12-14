import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Drag extends Application{
    Vector2 mousePos = new Vector2(0, 0);
    int mouse_Click_Flag = 0;
    Rectangle rect;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Test");
        rect = new Rectangle(100,100);
        rect.setX(110);
        rect.setY(220);
        rect.setFill(Color.BLUE);
        Pane root = new Pane();
        root.getChildren().add(rect);
        /*ドラッグ操作
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(17), event -> doMoveAction(event))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
        Scene scene = new Scene(root);
        //クリックした時のフラグの変動
        System.out.println("ああああ");
        scene.setOnMouseClicked(event -> moveObject(event));
        scene.setOnMouseDragged(event -> moveObject(event));
        scene.setOnMouseReleased(event -> releaseObject(event));
        stage.setScene(scene);
        stage.show();
    }
    void releaseObject(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && mouse_Click_Flag == 1) {
            mouse_Click_Flag = 0;
        }
    }
    void moveObject(MouseEvent event) {
        //if(event.getEventType() == MouseEvent.MOUSE_PRESSED /*&&
        //    event.getButton() == MouseButton.PRIMARY*/) {
            double rectX = rect.getX();
            double rectY = rect.getY();
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double distance = Math.sqrt(Math.pow(rectX - mouseX, 2) + Math.pow(rectY - mouseY, 2));
            if(distance <= 50)mouse_Click_Flag = 1;
            else mouse_Click_Flag = 0;
            System.out.println(event.getX() + ":" + event.getY());
        //}
        /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
            mouse_Click_Flag == 1) {
                rect.setX(event.getSceneX());
                rect.setY(event.getSceneY());
        }
    }
}