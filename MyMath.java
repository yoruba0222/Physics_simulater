public class MyMath {
          //インスタンス化を不可にする
          private MyMath() {}

          //二点間の距離を求める
          public static double getDistance(double x1, double y1, double x2, double y2) {
                    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
          }

          //ベクトルの正規化を行う
          public static Vector2 normalizationVector(Vector2 vec) {
                    double vecLength = getDistance(vec.x, vec.y, 0, 0);
                    vec.x /= vecLength; vec.y /= vecLength;
                    return vec;
          }

          //ベクトルのノルムを取得する
          public static double getNorm(Vector2 vec) {
                    return getDistance(vec.x, vec.y, 0, 0);
          }

          public static Vector2 getMinPosition(Vector2 startPos, Vector2 endPos, Vector2 point) {

                    Vector2 leastPosition = new Vector2();

                    //startPos.xの方がendPos.yよりも常に小さいようにする.
                    if (startPos.x > endPos.x) {
                              double tmp = startPos.x;
                              startPos.x = endPos.x;
                              endPos.x = tmp;
                              tmp = startPos.y;
                              startPos.y = endPos.y;
                              endPos.y = tmp;
                    }

                    //計算フェーズ
                    double m = (endPos.y - startPos.y) / (endPos.x - startPos.x);
                    double crossX = (200.0+200.0*m+m*m*startPos.x-m*startPos.y) / (1+m*m);
                    double crossY = m*crossX-m*startPos.x+startPos.y;
                    //double leastDis = MyMath.getDistance(crossX, crossY, pointX(), pointY());
                    double startDis = MyMath.getDistance(startPos.x, startPos.y, point.x, point.y);
                    double endDis = MyMath.getDistance(endPos.x, endPos.y, point.x, point.y);
                    
                    // 最短距離求めて最短座標求めるフェーズ
                    if  (crossX > startPos.x && crossX < endPos.x) {
                              leastPosition.init(crossX, crossY);
                              //System.out.println(originPoint.getCenterX());
                    }
                    else if (startDis < endDis) {
                              leastPosition.init(startPos.x, startPos.y);
                    }
                    else if(endDis < startDis) {
                              leastPosition.init(endPos.x, endPos.y);
                    }

                    return leastPosition;
                    
          }
          public static boolean get(Vector2 startPos, Vector2 endPos, Vector2 point) {

                    Vector2 leastPosition = new Vector2();

                    //startPos.xの方がendPos.yよりも常に小さいようにする.
                    if (startPos.x > endPos.x) {
                              double tmp = startPos.x;
                              startPos.x = endPos.x;
                              endPos.x = tmp;
                              tmp = startPos.y;
                              startPos.y = endPos.y;
                              endPos.y = tmp;
                    }

                    //計算フェーズ
                    double m = (endPos.y - startPos.y) / (endPos.x - startPos.x);
                    double crossX = (200.0+200.0*m+m*m*startPos.x-m*startPos.y) / (1+m*m);
                    double crossY = m*crossX-m*startPos.x+startPos.y;
                    //double leastDis = MyMath.getDistance(crossX, crossY, pointX(), pointY());
                    double startDis = MyMath.getDistance(startPos.x, startPos.y, point.x, point.y);
                    double endDis = MyMath.getDistance(endPos.x, endPos.y, point.x, point.y);
                    
                    // 最短距離求めて最短座標求めるフェーズ
                    if  (crossX > startPos.x && crossX < endPos.x) {
                              leastPosition.init(crossX, crossY);
                              //System.out.println(originPoint.getCenterX());
                    }
                    else if (startDis < endDis) {
                              leastPosition.init(startPos.x, startPos.y);
                    }
                    else if(endDis < startDis) {
                              leastPosition.init(endPos.x, endPos.y);
                    }

                    return leastPosition;
                    
          }
}
