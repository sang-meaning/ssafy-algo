import java.util.Scanner;

public class Solution_1247_정영훈 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] arr=new int[10];
        for (int i = 0; i < 10; i++) {
            int t=Integer.parseInt(sc.next());
            int n=sc.nextInt();
            int m=sc.nextInt();
            sc.nextLine();
            int answer=dfs(n,m);
            arr[i]=answer;

            
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("#"+(i+1)+" "+arr[i]);
            
        }
    }

    public static int dfs(int n, int m){
        if(m==0) return 1;
        return dfs(n,m-1)*n;
    }
}
