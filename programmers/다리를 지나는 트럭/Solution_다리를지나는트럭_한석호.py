from collections import deque

def solution(bridge_length, weight, truck_weights):
    time = 0
    # 다리를 표현하는 큐 (초기에는 비어있으므로 0으로 채움)
    bridge = deque([0] * bridge_length)
    # 대기 트럭도 큐로 변환
    trucks = deque(truck_weights)
    
    current_weight = 0  # 현재 다리 위 트럭들의 무게 합
    
    while bridge:
        time += 1
        
        # 1초가 지날 때마다 다리의 맨 앞 요소(트럭 또는 0)가 나감
        exited = bridge.popleft()
        current_weight -= exited
        
        # 대기 중인 트럭이 남아있는 경우
        if trucks:
            # 다음 트럭이 올라갔을 때 무게 제한을 넘지 않는지 확인
            if current_weight + trucks[0] <= weight:
                truck = trucks.popleft()
                bridge.append(truck)
                current_weight += truck
            else:
                # 올라가지 못하면 0을 넣어서 다리 길이를 유지 (시간 흐름 표현)
                bridge.append(0)
                
    return time

# 테스트 실행
bridge_length = 2
weight = 10
truck_weights = [7, 4, 5, 6]

print("정답:", solution(bridge_length, weight, truck_weights))  # 출력: 8