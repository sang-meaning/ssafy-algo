#import java.util.Scanner;
#import java.util.Deque;
#import java.util.ArrayDeque;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int test_case =1; test_case<=T; test_case++){
            Deque<String> que1 = new ArrayDeque<>();
            Deque<String> que2 = new ArrayDeque<>(); // 큐 생성 두개만
            int samples= sc.nextInt();
            for (int i=0;i<(samples+1)/2;i++){ // 앞 반수 +1(홀수인 경우)
                que1.offer(sc.next());
            }
            for (int i=0;i<samples/2;i++){ // 뒤 반수 (홀수에도 앞보다 1 적은 수)
                que2.offer(sc.next());
            }

            sb.append("#").append(test_case).append(" "); // 테케 문자 입력
            for (int i = 0; i<samples;i++){ // StringBuilder에 바로 쌓기
                if (i%2==0){ // que1이 앞에 오므로
                    sb.append(que1.poll());
                }else{
                    sb.append(que2.poll());
                }
                if(i!=samples-1){ // 뒤에 띄어쓰기 방지
                    sb.append(" ");
                }
            }
            sb.append('\n');

        }
        System.out.print(sb);
        sc.close();
        return;

    }

}