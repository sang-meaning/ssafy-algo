from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0
    wait = deque(truck_weights)
    bridge = deque()
    time = 0
    sum_weight = 0

    while wait or bridge:
        time += 1
        if bridge and time - bridge[0][1] == bridge_length:
            truck = bridge.popleft()
            sum_weight -= truck[0]
            
        if wait and sum_weight + wait[0] <= weight:
            truck = wait.popleft()
            bridge.append((truck, time))
            sum_weight += truck

    answer = time

    return answer