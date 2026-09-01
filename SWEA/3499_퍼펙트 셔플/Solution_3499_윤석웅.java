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
            Deque<String> que2 = new ArrayDeque<>();
            int samples= sc.nextInt();
            for (int i=0;i<(samples+1)/2;i++){
                que1.offer(sc.next());
            }
            for (int i=0;i<samples/2;i++){
                que2.offer(sc.next());
            }

            sb.append("#").append(test_case).append(" ");
            for (int i = 0; i<samples;i++){
                if (i%2==0){
                    sb.append(que1.poll());
                }else{
                    sb.append(que2.poll());
                }
                if(i!=samples-1){
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