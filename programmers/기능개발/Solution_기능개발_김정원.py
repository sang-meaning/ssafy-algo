def solution(progresses, speeds):
    answer = []

    while len(progresses) != 0:

        # 하루 진행
        for i in range(len(progresses)):
            progresses[i] += speeds[i]

        # 맨 앞이 완료됐을 때만 배포
        if progresses[0] >= 100:
            count = 0

            while len(progresses) > 0 and progresses[0] >= 100:
                del progresses[0]
                del speeds[0]
                count += 1

            answer.append(count)

    return answer