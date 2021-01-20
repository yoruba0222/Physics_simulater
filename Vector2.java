public class Vector2 {
          double x;
          double y;

          //コンストラクタその１
          public Vector2() {}
          // コンストラクタその２
          public Vector2(double x, double y) {
              this.x = x;
              this.y = y;
          }

          // 値の代入し直し
          public void init(double x, double y) {
                    this.x = x;
                    this.y = y;
          }

          // 逆ベクトルの取得
          public Vector2 inverse() {
                    return new Vector2((-1)*this.x, (-1)*this.y);
          }

          // ベクトルの外積
          public static double cross(Vector2 vec0, Vector2 vec1) {
                    return vec0.x * vec1.y - vec0.y * vec1.x;
          }

          // ２点を結ぶベクトルを求める(→この向きです)
          public static Vector2 sub(Vector2 vec0, Vector2 vec1) {
                    return new Vector2(vec0.x-vec1.x, vec0.y-vec1.y);
          }

          // 二つのベクトルを比べる.
          public boolean equals(Vector2 vec) {
                    if (this.x == vec.x && this.y == vec.y) return true;
                    else return false;
          }
          public static boolean equals(Vector2 vec0, Vector2 vec1) {
                    if (vec0.x == vec1.x && vec0.y == vec1.y) return true;
                    else return false;
          }

          // 二つのベクトルの間の大きさを取得する
          public static double getLength(Vector2 vec0, Vector2 vec1) {
                    return Math.sqrt(Math.pow(vec0.x-vec1.x, 2)+Math.pow(vec0.y-vec1.y, 2));
          }

          // 法線ベクトルを求める
          
}
