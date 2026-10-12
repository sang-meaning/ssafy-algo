import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static int N;
    static long[] x;
    static long[] y;
    static int[] parents;

    static class Edge {

        int start;
        int end;
        long distance;

        Edge(int start, int end, long distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    static int find(int x) {

        if(parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            N = sc.nextInt();

            x = new long[N];
            y = new long[N];

            for(int i=0; i<N; i++) {
                x[i] = sc.nextLong();
            }

            for(int i=0; i<N; i++) {
                y[i] = sc.nextLong();
            }

            double E = sc.nextDouble();

            List<Edge> edges = new ArrayList<>();

            // 모든 섬 사이의 간선 만들기
            for(int i=0; i<N; i++) {

                for(int j=i+1; j<N; j++) {

                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long distance = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, distance));
                }
            }

            // 거리 작은 순으로 정렬
            Collections.sort(edges, new Comparator<Edge>() {

                @Override
                public int compare(Edge o1, Edge o2) {
                    return Long.compare(o1.distance, o2.distance);
                }
            });

            parents = new int[N];

            for(int i=0; i<N; i++) {
                parents[i] = i;
            }

            long total = 0;
            int count = 0;

            for(int i=0; i<edges.size(); i++) {

                Edge edge = edges.get(i);

                if(union(edge.start, edge.end)) {

                    total += edge.distance;
                    count++;

                    if(count == N-1) {
                        break;
                    }
                }
            }

            long answer = Math.round(total * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}