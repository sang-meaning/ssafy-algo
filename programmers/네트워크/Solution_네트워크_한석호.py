from collections import deque

def solution(n, computers):
    answer = 0
    visited = [False] * n

    for i in range(n):
        if not visited[i]:
            queue = deque([i])
            visited[i] = True
            
            while queue:
                current = queue.popleft()
                for next_node in range(n):
                    if computers[current][next_node] == 1 and not visited[next_node]:
                        visited[next_node] = True
                        queue.append(next_node)
            
            answer += 1  # 하나의 네트워크 탐색 완료

    return answer