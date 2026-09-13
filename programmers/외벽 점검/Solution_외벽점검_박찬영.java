import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int[] extendedWeak;
    int n, weakLen;
    boolean[] visited;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weakLen = weak.length;
        this.visited = new boolean[dist.length];

        extendedWeak = new int[weakLen * 2];
        for (int i = 0; i < weakLen; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + weakLen] = weak[i] + n;
        }

        int[] permDist = new int[dist.length];

        permutation(0, dist, permDist);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    void permutation(int depth, int[] dist, int[] permDist) {
        if (depth == dist.length) {
            check(permDist);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permDist[depth] = dist[i];
                permutation(depth + 1, dist, permDist);
                visited[i] = false; 
            }
        }
    }

    void check(int[] permDist) {
        for (int start = 0; start < weakLen; start++) {
            int friendCount = 1; 
            int coverage = extendedWeak[start] + permDist[friendCount - 1];


            for (int i = start; i < start + weakLen; i++) {
                if (extendedWeak[i] > coverage) {
                    friendCount++; 

                    if (friendCount > permDist.length) {
                        break;
                    }

                    coverage = extendedWeak[i] + permDist[friendCount - 1];
                }
            }

            if (friendCount <= permDist.length) {
                answer = Math.min(answer, friendCount);
            }
        }
    }
}