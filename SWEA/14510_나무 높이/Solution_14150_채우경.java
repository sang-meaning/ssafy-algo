import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=1;t<=T;t++){

            int max=0;
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i=0;i<N;i++){
                arr[i]=Integer.parseInt(st.nextToken());

                max= Math.max(max,arr[i]);
            }
            int sum=0;
            for (int i=0;i<N;i++){
                sum+=max-arr[i];
            }

            int result=0;
            int two = sum/2;// 2 > 1
            int one = sum%2;// 2

            while(two>one+1){//
                two--;
                one+=2;
            }

            //
            if(two>one) {
                result = 2 * two;
            }else if(two<one){
                result = one+two ;
            }else
                result=2*two;


            System.out.println("#"+t+" "+result);

        }
        //N개의 나무
        // 첫날 1, 둘째날 2, 셋째날 1
        // 홀수 1 짝수 2
        // 물을 안줄수도있음
        // 2 1를 골고로 가져야하니까 모든 값합쳐서 2 1를 고르게 배치
        // 2가 더 많으면 하루 +1

        //2 , 4 2

    }
}