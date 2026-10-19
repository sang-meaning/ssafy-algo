import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static List<Integer>[] taller;
    static List<Integer>[] shorter;

    static int bfs(int start, List<Integer>[] graph) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                queue.offer(next);
                count++;
            }
        }

        return count;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = fs.nextInt();
            int M = fs.nextInt();

            taller = new ArrayList[N + 1];
            shorter = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                // a가 b보다 작다.
                taller[a].add(b);
                shorter[b].add(a);
            }

            int answer = 0;

            for (int student = 1; student <= N; student++) {
                int tallerCount = bfs(student, taller);
                int shorterCount = bfs(student, shorter);

                if (tallerCount + shorterCount == N - 1) {
                    answer++;
                }
            }

            sb.append('#').append(tc)
              .append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int pointer = 0;
        private int length = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (pointer >= length) {
                length = in.read(buffer);
                pointer = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        int nextInt() throws IOException {
            int c;

            do {
                c = read();
            } while (c != -1 && c <= ' ');

            if (c == -1) {
                throw new EOFException();
            }

            int value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }
    }
}