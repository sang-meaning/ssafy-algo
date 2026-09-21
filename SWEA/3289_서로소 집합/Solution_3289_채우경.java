import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int M;
    static int[] parent;
    static int[] dx= {0,0,1,-1};
    static int[] dy= {1,-1,0,0};
    static boolean flag;
    static int cnt=0;
    static boolean[][] visited;

    public static void main(String args[]) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T =Integer.parseInt(st.nextToken());
        for(int t=1;t<=T;t++) {

            st = new StringTokenizer(br.readLine());


            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parent=new int[N+1];
            for (int i = 1; i <= N; i++) {//기본세팅
                parent[i] = i;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            for(int i=0;i<M;i++){
                st= new StringTokenizer(br.readLine());
                int type=Integer.parseInt(st.nextToken());
                int a=Integer.parseInt(st.nextToken());
                int b= Integer.parseInt(st.nextToken());

                if (type==0){
                    union(a,b);
                }else if (type==1){
                    if(find(a)==find(b)){
                        sb.append(1);
                    }else
                        sb.append(0);
                }

            }
            /*
            * */
            System.out.println(sb);
        }
    }
    static int find(int x) {// 부모찾기
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);//다르면
    }

    // Union: x와 y가 속한 두 집합을 합침
    static void union(int x, int y) { // 부모가 다르면 뒤꽁무늬에 붙여
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX; // 한 쪽 부모를 다른 쪽에 연결
        }
    }
}
/*
 *
 *
 * */
