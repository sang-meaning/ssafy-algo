def solution(elements):
    n = len(elements)
    # 원형 수열을 표현하기 위해 리스트를 2배로 이어붙임
    extended = elements * 2
    
    # 중복을 제거할 set 생성
    sums_set = set()
    
    # length: 부분 수열의 길이 (1부터 n까지)
    for length in range(1, n + 1):
        # i: 부분 수열의 시작 인덱스
        for i in range(n):
            # i부터 i+length까지의 연속 부분 수열의 합을 구함
            sub_sum = sum(extended[i : i + length])
            sums_set.add(sub_sum)
            
    return len(sums_set)