import java.util.Arrays;

public class Otest {
          public static void main(String[]args) {
                    double [] t = {-11, 19,2, 3, 180};
                    System.out.println(minIndex(t));
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
