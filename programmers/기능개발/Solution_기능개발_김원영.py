from collections import deque
def solution(progresses, speeds):
    answer = []
    deploy = []
    
    for i in range(len(progresses)):
        temp = 100-progresses[i]
        if not (temp%speeds[i]):
            deploy.append(temp/speeds[i])
        else:
            deploy.append((temp//speeds[i])+1)
            
    q =deque(deploy)
    while(q):
        max = q.popleft()
        count =1
        
        while(q and max>= q[0]):
            q.popleft()
            count += 1
            
        answer.append(count)
    return answer