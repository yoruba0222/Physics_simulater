import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GJK {
          // shapeAが引かれる対象,shapeBが逆ベクトルの計算必要があるやつ
          public boolean getCollisionJudge(Shape shapeA, Shape shapeB) {

                    boolean collisionJudge = false;
                    Rectangle rect;
                    Circle circle;

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
                    Vector2 firstSmPosition = new Vector2(
                              SMF.getSupportMapping(shapeA, vec0).x - SMF.getSupportMapping(shapeB, vec0.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec0).y - SMF.getSupportMapping(shapeB, vec0.inverse()).y
                    );
                    
                    // 求めた視点から原点を結ぶベクトルを求める
                    Vector2 vec1 = firstSmPosition.inverse();

                    // そのベクトルでミンコフスキー差の図形のサポート写像を求める
                    Vector2 secondSmPosition = new Vector2(
                              SMF.getSupportMapping(shapeA, vec1).x - SMF.getSupportMapping(shapeB, vec1.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec1).y - SMF.getSupportMapping(shapeB, vec1.inverse()).y
                    );

                    // 求めた二つの支点を結び、その線分上の原点との最近点を求める.
                    Vector2 leastPosition = MyMath.getMinPosition(
                              firstSmPosition, secondSmPosition, new Vector2(0, 0)
                    );

                    // その線分上の最近点から原点を結ぶベクトルを求める
                    Vector2 vec2 = leastPosition.inverse();

                    // そのベクトルでミンコフスキーさを求めた図形のサポート写像を求める
                    Vector2 thirdPosition = new Vector2(
                              SMF.getSupportMapping(shapeA, vec2).x - SMF.getSupportMapping(shapeB, vec2.inverse()).x,
                              SMF.getSupportMapping(shapeA, vec2).y - SMF.getSupportMapping(shapeB, vec2.inverse()).y
                    );


                    return collisionJudge;
          }
}
