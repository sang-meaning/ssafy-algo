import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Solution_26059_정영훈{
    static Map<Integer, Integer> map;
    final static int NONE=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        int maxWeight=0;
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int n=Integer.parseInt(st.nextToken());
            int lo=Integer.parseInt(st.nextToken());
            int hi=Integer.parseInt(st.nextToken());
            map=new HashMap<>();
            st=new StringTokenizer(br.readLine()," ");
            for (int i = 0; i < n; i++) {
                int fruit=Integer.parseInt(st.nextToken());
                maxWeight=Math.max(maxWeight, fruit);
                map.put(fruit, map.getOrDefault(fruit, 0)+1);
            }
            Integer[] fruits=map.keySet().toArray(new Integer[map.size()]);
            Arrays.sort(fruits);
            int absDiff=Integer.MAX_VALUE;
            for (int i = 1; i <= maxWeight; i++) {
                for (int j = i+1; j <= maxWeight; j++) {
                    int diff=findFruit(i, j, n, fruits, lo, hi);
                    absDiff=Math.min(absDiff, Math.abs(diff));
                }
            }
                
            if(absDiff==NONE) absDiff=-1;
            sb.append("#"+test_case+" "+absDiff+"\n");

            
        }
        
        System.out.println(sb.toString());
    }
    public static int findFruit(int k1, int k2, int n, Integer[] fruits, int lo, int hi){
        int top=0;
        int middle=0;
        int bottom=0;
        for (int fruit : fruits) {
            if (fruit<k1) {
                bottom+=map.get(fruit);
            }else if (fruit>=k1 && fruit<k2) {
                middle+=map.get(fruit);
            }else{
                top+=map.get(fruit);
            }
        }

        if(top<lo || top>hi || middle<lo || middle>hi || bottom<lo || bottom>hi)return NONE;
        int minN=Math.min(top, Math.min(middle, bottom));
        int maxN=Math.max(top, Math.max(middle, bottom));
        return maxN-minN;
        

    }
}