import javafx.event.ActionEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;

public class PhysicsPipeline {

    //field variables
    private double grabity = 9.8;   //acceleration
    private double timeStep = 0.0167;
    private double CoefficientOfRestitution = 0.810;

    private ArrayList<Shape> shapeList = new ArrayList<>();
    private ArrayList<Circle> circleList = new ArrayList<>();
    private ArrayList<Rectangle> rectList = new ArrayList<>();

    // 情報を更新または取得して反映させるために必要
    private HashMap<Shape,PhyInfo> info;

    // 言わずモナが衝突判定に必要
    GJK gjk = new GJK();

    // コンストラクタ
    public PhysicsPipeline(HashMap<Shape, PhyInfo> info) {
              this.info = info;
    }

    //this function must need to loop in timeline
    public void update(ActionEvent event) {

        // ナローフェーズは時間の都合上省略.

        // ブロードフェーズ
        // ここは本来ナローフェーズで検出された奴がやるところだが、そんな時間もないので2重for文でいきます。
        
        for (int i=0; i<circleList.size(); i++) {
                  for (int k=0; k<rectList.size(); k++) {
                            if (gjk.getCollisionJudge(rectList.get(k), circleList.get(i))) {

                                      //System.out.println("なぜかあたっとるぞ...");

                              // 拘束の解消
                              // まず撃力を求める
                              Vector2 impluseS1, impluseS2;   // 速度の撃力(四角形、円形)
                              double impluseA1, impluseA2;     // 角速度の撃力(四角形、円形)
                              double mass1 = info.get(rectList.get(k)).getMass();         // 質量1
                              double mass2 = info.get(circleList.get(i)).getMass();       //質量2
                              double inertia1 = info.get(rectList.get(k)).getInertia();
                              double inertia2 = info.get(circleList.get(i)).getInertia();
                              double cor = CoefficientOfRestitution;  //反発係数
                              Vector2 unitNormalVector = gjk.getContactNormalVector().normal();     // 単位法線接触ベクトル
                              Vector2 radius1 = Vector2.sub(gjk.getContactNormalVector(), info.get(rectList.get(k)).getCenterPos());        // 重心から接触地点までのベクトル1
                              Vector2 radius2 = Vector2.sub(gjk.getContactNormalVector(), info.get(circleList.get(i)).getCenterPos());      // 重心から接触地点までのベクトル2
                              Vector2 relativeVelocity1 = Vector2.sub(info.get(circleList.get(i)).getSpeed(), info.get(rectList.get(k)).getSpeed());  // 四角形から見た相対速度
                              Vector2 relativeVelocity2 = Vector2.sub(info.get(rectList.get(k)).getSpeed(), info.get(circleList.get(i)).getSpeed());   // 円形から見た相対速度

                              // 速度の撃力
                              impluseS1 =
                              (relativeVelocity1.multiple((1+cor)*((mass1*mass2)/(mass1+mass2))));
                              impluseS2 =
                              (relativeVelocity2.multiple((1+cor)*((mass1*mass2)/(mass1+mass2))));

                              

                              // 角速度の撃力
                              impluseA1 = 
                              ((1+cor)*Vector2.inner(unitNormalVector, relativeVelocity1)) /
                              ((1/mass1) + (1/mass2) + Vector2.inner(radius1.multiple(Vector2.cross(radius1, unitNormalVector)/inertia1),unitNormalVector) +
                              Vector2.inner(radius2.multiple(Vector2.cross(radius2, unitNormalVector)/inertia2), unitNormalVector));
                              impluseA2 = 
                              ((1+cor)*Vector2.inner(unitNormalVector, relativeVelocity2)) /
                              ((1/mass1) + (1/mass2) + Vector2.inner(radius1.multiple(Vector2.cross(radius1, unitNormalVector)/inertia1),unitNormalVector) +
                              Vector2.inner(radius2.multiple(Vector2.cross(radius2, unitNormalVector)/inertia2), unitNormalVector));

                              // 撃力求めたのでそれを分解して新しい角速度と速度に変換する
                              // 新しい四角形の速度
                              info.get(rectList.get(k)).setSpeed(Vector2.add(info.get(rectList.get(k)).getSpeed(), impluseS1.multiple(1/mass1)));
                              // 新しい円形の速度
                              info .get(circleList.get(i)).setSpeed(Vector2.add(info.get(circleList.get(i)).getSpeed(), impluseS2.multiple(1/mass2)));
                              // 新しい四角形の角速度
                              info.get(rectList.get(k)).setAngular(info.get(rectList.get(k)).getAngular()+(impluseA1/mass1));
                              // 新しい円形の速度
                              info.get(circleList.get(i)).setAngular(info.get(circleList.get(i)).getAngular()+(impluseA2/mass2));

                            }
                  }
        }


          System.out.println(info.get(rectList.get(0)).getAngular());
        
        

        // 物理法則に従わせてやるぜ
        for (Rectangle rect : rectList) {
                  Vector2 speedDifference = new Vector2(0, grabity*timeStep);
                  info.get(rect).setSpeed(info.get(rect).getSpeed().add(speedDifference));
        }
        for (Circle circle : circleList) {
          Vector2 speedDifference = new Vector2(0, grabity*timeStep);
          info.get(circle).setSpeed(info.get(circle).getSpeed().add(speedDifference));
        }

        // 位置と角度情報の更新
        for (Rectangle rect : rectList) {
                  //System.out.println("rectX:"+rect.getX()+", rectY:"+rect.getY());
                  rect.setRotate(rect.getRotate()+(info.get(rect).getAngular()*timeStep*-100));
                  rect.setX(rect.getX()+(info.get(rect).getSpeed().x*timeStep));
                  rect.setY(rect.getY()+(info.get(rect).getSpeed().y*timeStep));
        }
        for (Circle circle : circleList) {
                  //System.out.println("circleX:"+circle.getCenterX()+", circleY:"+circle.getCenterY());
                  circle.setRotate(circle.getRotate()+info.get(circle).getAngular()*timeStep);
                  circle.setCenterX(circle.getCenterX()+(info.get(circle).getSpeed().x*timeStep));
                  circle.setCenterY(circle.getCenterY()+(info.get(circle).getSpeed().y*timeStep));
        }

    }

    // 撃力計算による位置情報



    //フェーズ

    //add shape to shapeList
    public void addPhysicalShape(Shape shape) {
          shapeList.add(shape);
          if (shape.toString().contains("Circle")) circleList.add((Circle)shape);
          else rectList.add((Rectangle)shape);
    }

    public void init() {
              this.initPosition();
              this.initState();

              for (Rectangle rect : rectList) {
                        info.get(rect).init();
              }
              for (Circle circle: circleList) {
                        info.get(circle).init();
              }
    }

    public void initState() {
          for (Rectangle rect : rectList) {
                    rect.setRotate(0);;
          }
    }

      public void initPosition() {
        for(Circle circle : circleList) {
                  circle.setCenterX(info.get(circle).getInitPos().x);
                  circle.setCenterY(info.get(circle).getInitPos().y);
        }
        for(Rectangle rect : rectList) {
          rect.setX(info.get(rect).getInitPos().x);
          rect.setY(info.get(rect).getInitPos().y);
        }
      }

      // アクセサー
      public void setCoefficientOfRestitution(double value) {
                this.CoefficientOfRestitution = value;
      }
}