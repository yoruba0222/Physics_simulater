import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class ExpandedPane extends AnchorPane{
          public void setX(Node shape, double x) {
                    AnchorPane.setLeftAnchor(shape, x);
          }
          public void setY(Node shape, double y) {
                    AnchorPane.setBottomAnchor(shape, y);
          }
          public void setPosition(Node shape, double x, double y) {
                    AnchorPane.setLeftAnchor(shape, x);
                    AnchorPane.setBottomAnchor(shape, y);
          }
}
