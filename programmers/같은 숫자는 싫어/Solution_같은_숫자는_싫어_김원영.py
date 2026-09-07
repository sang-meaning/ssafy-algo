def solution(arr):
    answer = []
    answer.append(arr[0])
    for i in range(len(arr)):
        if arr[i] != answer[-1]:
            answer.append(arr[i])
        else:
            continue

    return answer