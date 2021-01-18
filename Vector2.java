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
}
