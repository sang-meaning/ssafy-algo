def solution(n, computers):
    visited = [False] * n
    map = []
    for i in range(n):
        map.append([])
    for i in range(n):
        for j in range(n):
            if computers[i][j] == 1:
                map[i].append(j)
    def dfs(x):   
        if visited[x] == False:
            visited[x] = True
        else:
            return
            
        for element in map[x]:
            if visited[element] == False:
                dfs(element)
            else:
                continue
        return

    answer = 0
    for i in range(n):
        if visited[i] == False:
            dfs(i)
            answer += 1
    return answer