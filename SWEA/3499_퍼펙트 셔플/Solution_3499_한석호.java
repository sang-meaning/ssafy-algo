import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Solution_3499_한석호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();

            String[] word = new String[N];
            for (int i = 0; i < N; i++) {
                word[i] = sc.next();
            }

            // a와 b 배열의 크기 설정
            int half = N / 2;
            int aSize = (N % 2 == 0) ? half : half + 1;
            int bSize = half;

            String[] a = new String[aSize];
            String[] b = new String[bSize];

            // word를 a와 b로 분할
            for (int i = 0; i < aSize; i++) {
                a[i] = word[i];
            }
            for (int i = 0; i < bSize; i++) {
                b[i] = word[aSize + i];
            }

            List<String> ans = new ArrayList<>();

            // alternating 삽입
            for (int i = 0; i < half; i++) {
                ans.add(a[i]);
                ans.add(b[i]);
            }

            // N이 홀수일 때 a의 마지막 요소 추가
            if (N % 2 == 1) {
                ans.add(a[aSize - 1]);
            }

            // 출력 (#t 요소1 요소2 ...)
            System.out.print("#" + t);
            for (String s : ans) {
                System.out.print(" " + s);
            }
            System.out.println();
        }

        sc.close();
    }
}