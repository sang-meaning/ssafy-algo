from collections import deque
def solution(elements):
    q = deque(elements)
    answer = []
    for i in range(1,len(q)+1):
        for j in range(len(q)):
            answer.append(sum(list(q)[:i]))
            q.append(q[0])
            q.popleft()
    s = set(answer)
    return len(s)