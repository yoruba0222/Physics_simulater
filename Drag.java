import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import java.util.ArrayList;

public class Drag {
          private ArrayList<ExpandedCircle> circleList = new ArrayList<>();
          private ArrayList<ExpandedRectangle> rectList = new ArrayList<>();
          private ArrayList<Boolean> circle_Clicked_Frag = new ArrayList<>();
          private ArrayList<Boolean> rect_Clicked_Frag = new ArrayList<>();

          public void setDragedCircle(ExpandedCircle circle){
                    circleList.add(circle);
                    circle_Clicked_Frag.add(false);
          }
          public void setDragedRectangle(ExpandedRectangle rect) {
                    rectList.add(rect);
                    rect_Clicked_Frag.add(false);
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
          }
          public void moveObject(MouseEvent event) {
                    //rectangle processings
                    for(int i=0;i < rectList.size(); i++) {
                              double rectX = rectList.get(i).getPositionX();
                              double rectY = rectList.get(i).getPositionY();
                              double mouseX = event.getSceneX();
                              double mouseY = event.getSceneY();
                              double distance = Math.sqrt(Math.pow(rectX - mouseX, 2)
                                        + Math.pow(rectY - mouseY, 2));
                              System.out.println(distance);
                              if(distance <= 50)rect_Clicked_Frag.set(i, true);
                              else rect_Clicked_Frag.set(i, false);
                              System.out.println(event.getX() + ":" + event.getY());
              //}
              /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                      rect_Clicked_Frag.get(i) == true && rectList.get(i).toString()
                      == "ExpandedRectangle") {
                              rectList.get(i).setX(event.getSceneX());
                              rectList.get(i).setY(event.getSceneY());
                              rectList.get(i).position.x = event.getSceneX();
                              rectList.get(i).position.y = event.getSceneY();
                              return;
                              }
                    }
                    //circle processings
                    for(int i=0;i < circleList.size();i++) {
                              double circleX = circleList.get(i).getPositionX();
                              double circleY = circleList.get(i).getPositionY();
                              double mouseX = event.getSceneX();
                              double mouseY = event.getSceneY();
                              double distance = Math.sqrt(Math.pow(circleX - mouseX, 2)
                                      + Math.pow(circleY - mouseY, 2));
                              System.out.println(distance);
                              if(distance <= 50)circle_Clicked_Frag.set(i, true);
                              else circle_Clicked_Frag.set(i, false);
                              System.out.println(event.getX() + ":" + event.getY());
                              //}
                              /*else*/ if(event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                                circle_Clicked_Frag.get(i) == true && circleList.get(i).toString()
                                  == "ExpandedCircle") {
                                        circleList.get(i).setCenterX(event.getSceneX());
                                        circleList.get(i).setCenterY(event.getSceneY());
                                        circleList.get(i).position.x = event.getSceneX();
                                        circleList.get(i).position.y = event.getSceneY();
                                        return;
                              }
                    }
          }
}
