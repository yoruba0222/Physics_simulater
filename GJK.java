import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import java.util.Arrays;

public class GJK {
          // shapeAが引かれる対象,shapeBが逆ベクトルの計算必要があるやつ
          public static boolean getCollisionJudge(Shape shapeA, Shape shapeB) {
                    Rectangle rect;
                    Circle circle;

                    ArrayList<Vector2> smPos = new ArrayList<>();

                    // 図形によってセンターポイントを取得する時がバラバラなので図形によって分岐させる.
                    Vector2 centerA = new Vector2(0, 0), centerB = new Vector2(0, 0);
                    if (shapeA.toString().contains("Rectangle")) {
                              rect = (Rectangle)shapeA;
                              centerA = new Vector2(
                                        rect.getX() + rect.getWidth() / 2,
                                        rect.getY() + rect.getHeight() / 2
                              );
                    }
                    if (shapeB.toString().contains("Circle")) {
                              circle = (Circle)shapeB;
                              centerB = new Vector2(
                                        circle.getCenterX(),
                                        circle.getCenterY()
                              );
                    }

                    /*
                              ここからGJK Algorithmのフローチャート通りに進めていく.
                    */

                    // まず、引かれる対象となった図形の重心と原点を結ぶベクトルを求める.
                    Vector2 vec0 = centerA.inverse();

                    // そのベクトルでミンコフスキー差の図形のサポート写像を求める.
                    smPos.add(  new Vector2(
                              SMF.getSupportMapping(shapeA, vec0).x - SMF.getSupportMapping(shapeB, vec0.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec0).y - SMF.getSupportMapping(shapeB, vec0.inverse()).y
                    ));
                    
                    // 求めた支点から原点を結ぶベクトルを求める
                    Vector2 vec1 = smPos.get(0).inverse();

                    // そのベクトルでミンコフスキー差の図形のサポート写像を求める
                    smPos.add( new Vector2(
                              SMF.getSupportMapping(shapeA, vec1).x - SMF.getSupportMapping(shapeB, vec1.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec1).y - SMF.getSupportMapping(shapeB, vec1.inverse()).y
                    ));

                    // 求めた二つの支点を結び、その線分上の原点との最近点を求める.
                    Vector2 leastPosition = MyMath.getMinPosition(
                              smPos.get(0), smPos.get(1), new Vector2(0, 0)
                    );

                    // その線分上の最近点から原点を結ぶベクトルを求める
                    Vector2 vec2 = leastPosition.inverse();

                    // そのベクトルでミンコフスキーさを求めた図形のサポート写像を求むる
                    smPos.add( new Vector2(
                              SMF.getSupportMapping(shapeA, vec2).x - SMF.getSupportMapping(shapeB, vec2.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec2).y - SMF.getSupportMapping(shapeB, vec2.inverse()).y
                    ));

                    // これまでに求めた三支点を結んで作られた三角形は原点を含んでいるか？

                    int count = 0;
                    
                    while (true) {

                              //System.out.println("アルゴリズム実行中");

                              if (!JudgeInTriangle.getJudgeInTriangle(smPos.get(0), smPos.get(1), smPos.get(2), new Vector2(0, 0))) {


                                        // 最後に求めた視点とそれを挟む二つの線分と原点との最近点を求める
                                        Vector2 leastPos;
                                        Vector2 leastPos0 = MyMath.getMinPosition(smPos.get(0), smPos.get(2), new Vector2(0, 0));
                                        Vector2 leastPos1 = MyMath.getMinPosition(smPos.get(1), smPos.get(2), new Vector2(0, 0));

                                        if (MyMath.getNorm(leastPos0) < MyMath.getNorm(leastPos1)) {
                                                  leastPos = leastPos0;
                                        } else leastPos = leastPos1;

                                        // 最近点が三角形の頂点に重なっている?
                                        for (int i=0; i<3; i++) {
                                                  if (leastPos.equals(smPos.get(i))) {
                                                            //System.out.println(count);
                                                            return false;
                                                  }
                                                  Vector2 newsmPos = new Vector2(
                                                            SMF.getSupportMapping(shapeA, leastPos).x - 
                                                                      SMF.getSupportMapping(shapeB, leastPos.inverse()).x,
                                                            SMF.getSupportMapping(shapeA, leastPos).y - 
                                                                      SMF.getSupportMapping(shapeB, leastPos.inverse()).y
                                                  );

                                                  //既に選ばれている点から、先ほど選んだ点との距離が一番遠いものを消去する
                                                  int farestIndex = 0;
                                                  for (int k=0; k<3; k++) {
                                                            if (Vector2.getLength(leastPos, smPos.get(i)) > Vector2.getLength(leastPos, smPos.get(farestIndex))) farestIndex = i;
                                                  }
                                                  smPos.set(farestIndex, newsmPos);

                                                  count++;

                                                  if (count > 15) return false;

                                        }                  
                              } else {
                                        //System.out.println(count);
                                        /*
                                                  Johnson's Distance Algorithm
                                        */

                                        while (true) {

                                                  // 内包している単体で原点から一番近い点を求め、原点とその点を結んだベクトルを求める。
                                                  Vector2[] minPositions = new Vector2[smPos.size()];
                                                  minPositions[0] = new Vector2(
                                                            MyMath.getMinPosition(smPos.get(0), smPos.get(1), new Vector2(0, 0)).x,
                                                            MyMath.getMinPosition(smPos.get(0), smPos.get(1), new Vector2(0, 0)).y
                                                  );
                                                  for (int i=1; i<smPos.size()-1; i++) {
                                                            minPositions[i] = new Vector2(
                                                                      MyMath.getMinPosition(smPos.get(i), smPos.get(i+1), new Vector2(0, 0)).x,
                                                                      MyMath.getMinPosition(smPos.get(i), smPos.get(i+1), new Vector2(0, 0)).y
                                                            );
                                                  }
                                                  double[] distances = new double[3];
                                                  for (int i=0; i<3; i++) {
                                                            distances[i] = MyMath.getDistance(0, 0, minPositions[i].x, minPositions[i].y);
                                                  }
                                                  int minIndex = minIndex(distances);
                                                  Vector2 minVec = new Vector2(minPositions[minIndex].x, minPositions[minIndex].y);
                                                  smPos.add( new Vector2(
                                                            SMF.getSupportMapping(shapeA, minVec).x - SMF.getSupportMapping(shapeB, minVec.inverse()).x,
                                                            SMF.getSupportMapping(shapeA, minVec).y - SMF.getSupportMapping(shapeB, minVec.inverse()).y
                                                  ));

                                                  // 一つ前に求めた点と同じ座標に現在求めた座標が一致していたら終わり
                                                  if (smPos.get(smPos.size()-1).equals(smPos.get(smPos.size()-2))) break;              
                                        }

                                        // 貫通深度
                                        double penetrationDepth = MyMath.getNorm(smPos.get(smPos.size()-1));
                                        // 接触法線ベクトル
                                        Vector2 contactNormalVector = smPos.get(smPos.size()-1);
                                        
                                        return true;
                              }
                    }
          }

          public static int minIndex(double[] list) {
                    double[] listClone = list.clone();
                    Arrays.sort(list);
                    for (int i=0; i<list.length; i++) {
                              if (listClone[i] == list[0]) return i;
                    }
                    return 0;
          }
}