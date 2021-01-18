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
                double value;

                double theta = (
                          Math.acos(
                                    (vec0.x*vec1.x+vec0.y*vec1.y) / (MyMath.getNorm(vec0)*MyMath.getNorm(vec1))
                          )
                );

                value = MyMath.getNorm(vec0) * MyMath.getNorm(vec1) * Math.sin(theta);

                return value;
          }
}
