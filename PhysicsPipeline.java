import javafx.event.ActionEvent;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class PhysicsPipeline {

    //field variables
    private double time = 0;    //time until start simulation
    private double grabity = 9.8;   //acceleration

    private ArrayList<ExpandedCircle> circlesList = new ArrayList<>();
    private ArrayList<ExpandedRectangle> rectList = new ArrayList<>();

    //this function must need to loop in timeline
    public void update(ActionEvent event) {
        time += 0.0167;
        for(ExpandedCircle circle : circlesList) {
            circle.setCenterY(circle.getPositionY() + 0.5 * grabity * time * time);
        }
        for(ExpandedRectangle rect : rectList) {
            rect.setY(rect.getPositionY() + 0.5 * grabity * time * time);
        }
    }

    //フェーズ

    //add shape to shapeList
    public void addPhysicalShape(Shape shape) {
        if(shape.toString() == "ExpandedCircle") {
            circlesList.add((ExpandedCircle) shape);
        } else {
            rectList.add((ExpandedRectangle) shape);
        }
    }

      public void initTime() {
        this.time = 0;
      }

      public void initPosition() {
        for(ExpandedCircle circle : circlesList) {
          circle.setPositionX(circle.position.x);
          circle.setPositionY(circle.position.y);
        }
        for(ExpandedRectangle rect : rectList) {
          rect.setPositionX(rect.position.x);
          rect.setPositionY(rect.position.y);
        }
      }
}