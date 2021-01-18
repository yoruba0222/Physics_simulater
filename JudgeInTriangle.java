public class JudgeInTriangle{
          public static boolean getJudgeInTriangle(Vector2 vec0, Vector2 vec1, Vector2 vec2, Vector2 point) {

                    // 三角形の各辺のベクトルを得る
                    Vector2 edge0 = Vector2.sub(vec1, vec0);
                    Vector2 edge1 = Vector2.sub(vec2, vec1);
                    Vector2 edge2 = Vector2.sub(vec0, vec2);

                    // v0から見た辺の向きベクトルを得る(→この向きです)
                    Vector2 ce0 = Vector2.sub(vec1, vec0);
                    Vector2 ce1 = Vector2.sub(vec2, vec0);
                    int ccw = 1;

                    // それぞれの辺の位置関係を外積によって確認し、
                    // 時計回りか反時計回りかを判定.
                    // 時計回りの場合は、以後の判定のプラスマイナスを逆転させる
                    if (Vector2.cross(ce0, ce1) < 0) {
                              ccw = -1;
                    }

                    // 原点が三角形の辺の内側にあるかを判定
                    // 三つの辺の全てにおいて内側という判定の場合は
                    // 原点は三角形の内側に存在している
                    Vector2 cp0 = Vector2.sub(point, vec0);

                    if (Vector2.cross(edge0, cp0)*ccw <= 0) {
                              return false;
                    }

                    Vector2 cp1 = Vector2.sub(point, vec1);

                    if (Vector2.cross(edge1, cp1)*ccw <= 0) {
                              return false;
                    }

                    Vector2 cp2 = Vector2.sub(point, vec2);

                    if (Vector2.cross(edge2, cp2)*ccw <= 0) {
                              return false;
                    }

                    return true;
          }
}
