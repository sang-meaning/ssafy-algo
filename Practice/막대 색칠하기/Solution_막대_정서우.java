import java.io.*;

public class Solution_막대_정서우 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());

    int[] f = new int[n + 1];

    f[1] = 2;
    f[2] = 5;

    for (int i = 3; i < n + 1; i++) {
      f[i] = 2 * f[i - 1] + f[i - 2];
    }

    System.out.println(f[n]);
  }
}
