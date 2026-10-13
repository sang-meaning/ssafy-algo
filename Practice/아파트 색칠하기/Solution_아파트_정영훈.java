import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[][] D=new int[n][2];
        D[0][0]=1;
        D[0][1]=1;
        
        for(int i=1; i<n; i++){
            D[i][0]=D[i-1][0]+D[i-1][1];
            D[i][1]=D[i-1][0];
        }
        System.out.println(D[n-1][0]+D[n-1][1]);

    }
}