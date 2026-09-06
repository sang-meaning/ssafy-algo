import java.io.*;
import java.util.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
     
    static int check(String[] tree, int curLoc) {
        if(curLoc >= tree.length) {
            return 0;
        }
         
        int left = check(tree, curLoc * 2);
        int right = check(tree, curLoc * 2 + 1);

        if(left == 0 && left == 0) {
            boolean digitCheck = true;
            for(int i = 0 ; i < tree[curLoc].length() ; i++) {
                if(!Character.isDigit(tree[curLoc].charAt(i))) {
                    digitCheck = false;
                }
            }
             
            if(digitCheck) {
                return 1;
            }
        }
        else if(left == 1 && right == 1) {
            if(tree[curLoc].length() == 1) {
                switch(tree[curLoc].charAt(0)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    return 1;
                default:
                    return -1;
                }
            }
        }
        return -1;
    }
     
    public static void main(String[] args) throws Exception {
        int tc = 10;
         
        for(int i = 1 ; i <= tc ; i++) {
            int nodeNum = Integer.parseInt(br.readLine());
            int ans = 0;
            String[] tree = new String[nodeNum + 1];
             
            for(int j = 1 ; j <= nodeNum ; j++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                tree[j] = st.nextToken();
            }
            ans = check(tree, 1) == -1 ? 0 : 1;
             
            sb.append("#").append(i).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}