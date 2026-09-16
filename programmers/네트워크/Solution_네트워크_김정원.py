from collections import deque
answer = 0
queue = deque()

def bfs(start, visited, n, computers):
    queue = deque([start])
    visited.add(start)

    while queue:
        current = queue.popleft()

        for next_node in range(n):
            if computers[current][next_node] == 1 and next_node not in visited:
                visited.add(next_node)
                queue.append(next_node)


def solution(n, computers) :
    visited = set()
    answer = 0

    for node in range(n):
        if node not in visited:
            answer += 1
            bfs(node, visited, n, computers)
    return answer