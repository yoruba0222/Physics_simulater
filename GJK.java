import javafx.scene.shape.Shape;

public class GJK {
          // shapeAが引かれる対象,shapeBが逆ベクトルの計算必要があるやつ
          public boolean getCollisionJudge(Shape shapeA, Shape shapeB) {
                    boolean collisionJudge = false;

                    // 図形によってセンターポイントを取得する時がバラバラなので図形によって分岐させる.
                    Vector2 toCenterVec = new Vector2();
                    
                    return collisionJudge;
          }
}