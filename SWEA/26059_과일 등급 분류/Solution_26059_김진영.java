import java.util.*;
import java.io.*;
 
class Solution
{
     
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
     
    static int lo,hi;
    static int T,N;
    static int[] nums;
    static int result;
    static int max;
    static int min;
     
    public static void main(String[] args) throws Exception{
         
        T = Integer.parseInt(br.readLine());
         
        for(int test_case = 1;test_case<=T;test_case++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            lo = Integer.parseInt(st.nextToken());
            hi = Integer.parseInt(st.nextToken());
             
            nums = new int[N];
            result = Integer.MAX_VALUE;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
 
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
             
            for(int i=0;i<N;i++) {
                for(int j=i+1;j<N;j++) {
                    if(nums[i] > nums[j]) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
            }
             
            for(double flag1=0.5;flag1<nums[N-1];flag1+=1) {
                 
                for(double flag2=flag1+1;flag2<nums[N-1];flag2+=1) {
                    int cnt1=0;
                    int cnt2=0;
                    for(int i=0;i<N;i++) {
                        if(nums[i] < flag1) {
                            cnt1++;
                        }
                        if(nums[i] < flag2) {
                            cnt2++;
                        }
                    }
                     
                    if(cnt1 < lo || cnt1 > hi) {continue;}
                    if(cnt2-cnt1 < lo || cnt2-cnt1 > hi) {continue;}
                    if(N-cnt2 < lo || N-cnt2 > hi) {continue;}
     
                    min = Math.min(cnt1, Math.min(cnt2-cnt1, N-cnt2));
                    max = Math.max(cnt1, Math.max(cnt2-cnt1, N-cnt2));
                    result = Math.min(result, max-min);
                     
                }
            }
 
            sb.append("#")
            .append(test_case)
            .append(" ");
             
            if(result == Integer.MAX_VALUE) {
                sb.append("-1");
            }else {
                sb.append(result);
            }
            sb.append("\n");
             
        }   
        System.out.print(sb);
    }
}