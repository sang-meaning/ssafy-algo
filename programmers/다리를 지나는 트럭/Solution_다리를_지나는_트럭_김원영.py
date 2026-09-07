from collections import deque

def solution(bridge_length, weight, truck_weights):
    after = []
    goal = len(truck_weights)
    bridge = deque()
    before = deque(truck_weights)
    time = 0
    total_weight = 0
    
    for i in range(bridge_length):
        bridge.append(0)

    while len(after) != goal:
        time += 1
        
        if bridge and bridge[0] != 0:
            after.append(bridge[0])
            total_weight -= bridge[0]
            
        bridge.popleft()
        bridge.append(0)
        
        if before and total_weight + before[0] <= weight:
            bridge[-1] = before[0]
            before.popleft()
            total_weight += bridge[-1]
        else:
            continue

    return time