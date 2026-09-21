class Solution_네트워크_임성진 {
    private int[] parent;

    public int solution(int n, int[][] computers) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (computers[i][j] == 1) union(i, j);

        int answer = 0;
        for (int i = 0; i < n; i++) if (find(i) == i) answer++;
        return answer;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);   // 경로 압축
    }

    private void union(int a, int b) {
        a = find(a); b = find(b);
        if (a != b) parent[b] = a;
    }
}