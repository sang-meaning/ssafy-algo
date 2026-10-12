class Solution {
    static int[] points, distances, order;
    static boolean[] visited;
    static int w, d, answer;

    public int solution(int n, int[] weak, int[] dist) {
        w = weak.length;
        d = dist.length;
        answer = d + 1;

        distances = dist;
        order = new int[d];
        visited = new boolean[d];

        points = new int[w * 2];
        for (int i = 0; i < w; i++) {
            points[i] = weak[i];
            points[i + w] = weak[i] + n;
        }

        dfs(0);

        if(answer > d){
            answer = -1;
        }

        return answer;
    }

    static void dfs(int depth) {
        if (answer == 1) {
            return;
        }

        if (depth == d) {
            for (int start = 0; start < w; start++) {
                int count = 1;
                int end = points[start] + order[0];

                for (int i = start; i < start + w; i++) {
                    if (points[i] <= end) {
                        continue;
                    }

                    count++;

                    if (count > d) {
                        break;
                    }

                    end = points[i] + order[count - 1];
                }

                answer = Math.min(answer, count);
            }
            return;
        }

        for (int i = 0; i < d; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            order[depth] = distances[i];

            dfs(depth + 1);

            visited[i] = false;
        }
    }
}