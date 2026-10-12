from collections import deque 

def solution(progresses, speeds):
    answer = []
    
    end_time = []
    
    for i in range(len(progresses)):
        
        time = (100 - progresses[i]) // speeds[i]
        
        if (100 - progresses[i]) % speeds[i]:
            time += 1 
        
        end_time.append(time)
    
    
    queue = deque(end_time)
    
    while queue:
        
        results = 1
        
        time = queue.popleft()
        
        # 검사해야함
        while queue:

            if queue[0] <= time:
                queue.popleft()
                results += 1

            else:
                break
        
        answer.append(results)
            
    return answer