def isMove(truck, move, truck_weights, weight, bridge_length):
    # 조건 1. 다리를 건널 수 있는 트럭의 총 갯수
    if bridge_length < len(move) + 1:
        return False

    # 조건 2. 트럭의 무게 총합이 견딜 수 있는지
    # 이동 중인 트럭들의 무게 총합
    moveWeightSum = sum(truck_weights[t[0]] for t in move)

    if moveWeightSum + truck_weights[truck] > weight:
        return False

    return True


def solution(bridge_length, weight, truck_weights):
    answer = 0

    # 도착한 트럭
    com = []

    # 이동중인 트럭
    # [트럭 번호, 이동한 시간]
    move = []

    # 대기 중인 트럭
    ready = [i for i in range(len(truck_weights))]

    # 총 트럭 수
    truck_sum = len(truck_weights)

    while len(com) < truck_sum:
        # 1초씩 시간 증가
        answer += 1

        # 건너는 트럭의 큐를 확인하고 이동 시간 증가 시키기
        for truck in move:
            truck[1] += 1

        # 다리를 다 건넌 트럭이 있다면 도착한 트럭으로 이동 시키기
        if len(move) > 0 and move[0][1] >= bridge_length:
            com.append(move[0][0])
            move.pop(0)

        # 먼저 대기 큐의 첫번째 트럭이 다리에 무게가 올라갈 수 있는지 확인
        # 가능하다면 다리를 건너는 트럭 큐에 넣기, 대기 큐에서 제거
        if len(ready) != 0 and isMove(
            ready[0],
            move,
            truck_weights,
            weight,
            bridge_length
        ):
            truck = ready.pop(0)
            move.append([truck, 0])

    # 모든 트럭이 다리를 건너면 종료
    return answer