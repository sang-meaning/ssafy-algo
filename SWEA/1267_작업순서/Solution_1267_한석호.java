import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 총 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) break;
            
            StringTokenizer st = new StringTokenizer(line);
            int V = Integer.parseInt(st.nextToken()); // 정점 개수
            int E = Integer.parseInt(st.nextToken()); // 간선 개수

            // 인접 리스트 및 진입 차수(in-degree) 배열 초기화
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            int[] inDegree = new int[V + 1];

            // 간선 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph.get(from).add(to);
                inDegree[to]++; // 도착 정점의 진입 차수 증가
            }

            // 위상 정렬을 위한 큐 생성
            Queue<Integer> queue = new ArrayDeque<>();

            // 1. 진입 차수가 0인 정점을 모두 큐에 삽입
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc);

            // 2. 큐에서 정점을 하나씩 꺼내며 연결된 간선 제거
            while (!queue.isEmpty()) {
                int current = queue.poll();
                sb.append(" ").append(current);

                for (int next : graph.get(current)) {
                    inDegree[next]--; // 연결된 정점의 진입 차수 감소
                    
                    // 진입 차수가 0이 되면 큐에 삽입
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            // 결과 출력
            System.out.println(sb.toString());
        }
    }
}