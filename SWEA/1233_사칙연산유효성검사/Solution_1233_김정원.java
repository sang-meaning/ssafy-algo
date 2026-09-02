package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1233_김정원 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int test_case = 1; test_case <= 10; test_case++) {

            int N = Integer.parseInt(br.readLine());
            int answer = 1;

            // 트리 입력 받기
            // [노드번호] [값 또는 연산자]
            // [노드번호] [연산자] [자식노드번호1] [자식노드번호2]

            // 완전 이진 트리이므로
            // 왼쪽 자식 = index * 2
            // 오른쪽 자식 = index * 2 + 1
            Map<Integer, Integer> nodes = new HashMap<>();

            // 아스키코드로 연산자와 숫자를 같이 담기
            // * 42
            // + 43
            // - 45
            // / 47
            // 0~9 48 ~ 57
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                int node = Integer.parseInt(st.nextToken());
                int value = st.nextToken().charAt(0);

                nodes.put(node, value);
            }

            // 유효성 체크
            for (int i = 1; i <= N; i++) {

                boolean hasChild = i * 2 <= N;
                boolean operator = isOp(nodes.get(i));

                // 자식이 있는데 연산자가 아니면 잘못된 트리
                if (hasChild && !operator) {
                    answer = 0;
                    break;
                }

                // 자식이 없는데 연산자이면 잘못된 트리
                if (!hasChild && operator) {
                    answer = 0;
                    break;
                }
            }

            System.out.println(String.format("#%d %d", test_case, answer));
        }
    }

    static boolean isOp(int number) {
        return number == 42
                || number == 43
                || number == 45
                || number == 47;
    }
}