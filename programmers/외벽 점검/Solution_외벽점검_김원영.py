from itertools import permutations

def solution(n, weak, dist):
    weak_len = len(weak)
    answer = len(dist) + 1

    # 원형을 일자로 펴기
    weak = weak + [w + n for w in weak]

    # 시작 취약점을 하나씩 바꿔가면서 확인
    for start in range(weak_len):

        # 친구 투입 순서
        for friends in permutations(dist):
            count = 1
            position = weak[start] + friends[0]

            # 시작점부터 취약점 개수만큼 확인
            for i in range(start, start + weak_len):

                # 현재 친구가 갈 수 없는 취약점이면
                if weak[i] > position:
                    count += 1

                    # 친구를 다 사용한 경우
                    if count > len(dist):
                        break

                    position = weak[i] + friends[count - 1]

            answer = min(answer, count)

    if answer > len(dist):
        return -1

    return answer