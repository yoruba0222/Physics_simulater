import javafx.scene.input.MouseEvent;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton; 
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class DragE {
          private ArrayList<Circle> circleList = new ArrayList<>();
          private ArrayList<Rectangle> rectList = new ArrayList<>();
          private ArrayList<Polygon> triList = new ArrayList<>();
          private ArrayList<Boolean> circle_Clicked_Frag = new ArrayList<>();
          private ArrayList<Boolean> rect_Clicked_Frag = new ArrayList<>();
          private ArrayList<Boolean> tri_Clicked_Frag = new ArrayList<>();

          public void setScene(Scene scene) {
                    scene.setOnMouseClicked(event -> moveObject(event));
                    scene.setOnMouseDragged(event -> moveObject(event));
                    scene.setOnMouseReleased(event -> releaseObject(event));
          }

          public void setDragedCircle(Circle circle){
                    circleList.add(circle);
                    circle_Clicked_Frag.add(false);
          }
          public void setDragedRectangle(Rectangle rect) {
                    rectList.add(rect);
                    rect_Clicked_Frag.add(false);
          }
          public void setDragedPolygon(Polygon pol) {
                    triList.add(pol);
                    tri_Clicked_Frag.add(false);
          }

          // drag functions
          public void releaseObject(MouseEvent event) {
                    for(int i = 0;i < circle_Clicked_Frag.size();i++) {
                              if (event.getButton() == MouseButton.PRIMARY && circle_Clicked_Frag.get(i) == true) {
                                        circle_Clicked_Frag.set(i, false);
                                        return;
                              }
                    }
                    for(int i = 0;i < rect_Clicked_Frag.size();i++) {
                              if (event.getButton() == MouseButton.PRIMARY && rect_Clicked_Frag.get(i) == true) {
                                        rect_Clicked_Frag.set(i, false);
                                        return;
                              }                   
                    }
                    for(int i = 0;i < tri_Clicked_Frag.size();i++) {
                              if (event.getButton() == MouseButton.PRIMARY && tri_Clicked_Frag.get(i) == true) {
                                        tri_Clicked_Frag.set(i, false);
                                        return;
                              }
                    }
          }
          public void moveObject(MouseEvent event) {
                    //rectangle processings
                    for(int i=0;i < rectList.size(); i++) {

                              if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                                        double rectX = rectList.get(i).getX();
                                        double rectY = rectList.get(i).getY();
                                        double mouseX = event.getSceneX();
                                        double mouseY = event.getSceneY();
                                        double distance = Math.sqrt(Math.pow(rectX - mouseX, 2)
                                                  + Math.pow(rectY - mouseY, 2));
                                        //System.out.println(distance);
                                        if(distance <= 50) rect_Clicked_Frag.set(i, true);
                                        else rect_Clicked_Frag.set(i, false);
                                        //System.out.println(event.getX() + ":" + event.getY());
                              }
                    //}
                    /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                    rect_Clicked_Frag.get(i) == true /* && rectList.get(i).toString()
                    == "Rectangle" */) {
                              rectList.get(i).setX(event.getSceneX());
                              rectList.get(i).setY(event.getSceneY());
                              
                              
                              return;
                              }
                    }
                    //circle processings
                    for(int i=0;i < circleList.size();i++) {

                              

                              if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

                                        System.out.println(event.getEventType());

                                        double circleX = circleList.get(i).getCenterX();
                                        double circleY = circleList.get(i).getCenterY();
                                        double mouseX = event.getSceneX();
                                        double mouseY = event.getSceneY();
                                        double distance = Math.sqrt(Math.pow(circleX - mouseX, 2)
                                                + Math.pow(circleY - mouseY, 2));
                                        //System.out.println(distance);
                                        if(distance <= 50) circle_Clicked_Frag.set(i, true);
                                        else circle_Clicked_Frag.set(i, false);
                                        //System.out.println(event.getX() + ":" + event.getY());
                                        //}
                              }
                              /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                                circle_Clicked_Frag.get(i) == true /* && circleList.get(i).toString()
                                  == "Circle" */) {
                                        circleList.get(i).setCenterX(event.getSceneX());
                                        circleList.get(i).setCenterY(event.getSceneY());
                                        return;
                              }
                    }
                    for(int i=0;i < triList.size();i++) {
                              double triX = triList.get(i).getLayoutX();
                              double triY = triList.get(i).getLayoutY();
                              double mouseX = event.getSceneX();
                              double mouseY = event.getSceneY();
                              double distance = Math.sqrt(Math.pow(triX - mouseX, 2)
                                      + Math.pow(triY - mouseY, 2));
                              //System.out.println(distance);
                              if(distance <= 50)tri_Clicked_Frag.set(i, true);
                              else tri_Clicked_Frag.set(i, false);
                              //System.out.println(event.getX() + ":" + event.getY());
                              //}
                              /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                                        tri_Clicked_Frag.get(i) == true /* && circleList.get(i).toString()
                                        == "Circle" */) {
                                        triList.get(i).setLayoutX(event.getSceneX());
                                        triList.get(i).setLayoutY(event.getSceneY());
                                        return;
                              }
                    }
          }
}
