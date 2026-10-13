import java.util.*;
//git
class Solution{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] D=new int[n];
        D[0]=1;
        D[1]=2;
        for(int i=2; i<n; i++){
          D[i]=D[i-1]+D[i-2];

        }
        System.out.println(D[n-1]);

    }
}