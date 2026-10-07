
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy ={ 0, 0 ,-1,1};
    static int answer;
    static class Road implements Comparable<Road>{
        int x;
        int y;
        int cost;

        Road(int x, int y, int cost){
            this.x=x;
            this.y=y;
            this.cost=cost;
        }

        @Override
        public int compareTo(Road r1){
            return this.cost-r1.cost;
        }

    }
    static final int INF= Integer.MAX_VALUE;
    static PriorityQueue<Road> que ;
    static int N, T;

    static String b;

    public static void main(String[] args)throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t= 1; t<=T; t++){
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dist =  new int[N][N];

            for(int y= 0; y<N; y++){
                b = br.readLine();
                for(int x =0; x<N; x++){
                    map[y][x] = b.charAt(x)-'0';
                }
            }
            que = new PriorityQueue<>();

            for(int i = 0 ; i < N; i++){
                Arrays.fill(dist[i],INF);
            }

            que.offer(new Road(0,0,0));
			dist[0][0] = 0;
            while(!que.isEmpty()){
                Road r = que.poll();

                int x = r.x;
                int y =r.y;
                int cost = r.cost;

                if(dist[y][x] < cost) continue;

                if(x == N-1 && y ==N-1) {
                    answer =cost;
                }

                for(int d = 0 ; d<4; d++){
                    int nx = x +dx[d];
                    int ny = y +dy[d];
                    if(nx<0 || ny < 0 || nx>N-1 || ny>N-1) continue;

                    int nextcost = map[ny][nx] + cost;
					if(nextcost<dist[ny][nx]){
                    	dist[ny][nx] =nextcost;

                    	que.offer(new Road(nx,ny,nextcost));
                    }
                }
            }

            System.out.println("#"+t+" "+answer);





        }
    }
}