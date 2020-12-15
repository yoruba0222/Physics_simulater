public class MyMath {
    //インスタンス化を不可にする
    private MyMath() {}

    //二点間の距離を求める
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
