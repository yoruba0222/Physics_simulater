import javafx.event.ActionEvent;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class PhysicsPipeline {

    //field variables
    private double time = 0;    //time until start simulation
    private double grabity = 9.8;   //acceleration

    private ArrayList<Shape> shapePhysicalList;
    private ArrayList<Double> shapeTimes;

    PhysicsPipeline(Shape... shapes) {
        shapePhysicalList.addAll(Arrays.asList(shapes));
    }

    //this function must need to loop in timeline
    public void update(){}

    //add shape to shapeList
    public void addPhysicaLaw(Shape shape) {
        shapePhysicalList.add(shape);
    }
    public void addAllPhysicalLaw(Shape... shapes) {
        shapePhysicalList.addAll(Arrays.asList(shapes));
    }

    // event functions //
    // falling motion
    private void fallAction(ActionEvent event, Shape shape) {}
}