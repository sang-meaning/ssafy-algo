import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {

            int T = sc.nextInt();

            Queue<Integer> queue = new LinkedList<>();

            for(int i = 0; i < 8; i++) {
                queue.add(sc.nextInt());
            }

            int num = 1;

            while(true) {

                // 맨 앞 숫자 꺼내기
                int first = queue.poll();

                // 숫자 감소
                int next = first - num;

                // 0 이하라면 0으로 만들고 종료
                if(next <= 0) {
                    next = 0;
                    queue.add(next);
                    break;
                }

                // 계산한 값을 맨 뒤에 넣기
                queue.add(next);

                // 1 → 2 → 3 → 4 → 5 → 1
                num = num % 5 + 1;
            }

            System.out.print("#" + T);

            while(!queue.isEmpty()) {
                System.out.print(" " + queue.poll());
            }

            System.out.println();
        }
    }
}