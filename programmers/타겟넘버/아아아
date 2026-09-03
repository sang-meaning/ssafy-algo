answer = 0

def dfs(index, result, numbers, target) :
    global answer
    if index == len(numbers) :
        if result == target :
            answer += 1
            return
    else :
        dfs(index + 1, result + numbers[index], numbers, target)
        dfs(index + 1, result - numbers[index], numbers, target)


def solution(numbers, target):
    dfs(0, 0, numbers, target)
    return answer