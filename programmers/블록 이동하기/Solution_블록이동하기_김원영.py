from collections import deque

def solution(board):
    N = len(board)

    dx = [0,0,1,-1]
    dy = [1,-1,0,0]

    visited = set()
    queue = deque()

    # tail = (0,0), head = (0,1), time = 0
    queue.append((0,0,0,1,0))
    visited.add((0,0,0,1))

    while queue:
        tail_x,tail_y,head_x,head_y,time = queue.popleft()

        # 목적지 도착
        if (tail_x == N-1 and tail_y == N-1) or \
           (head_x == N-1 and head_y == N-1):
            return time

        # 상하좌우 이동
        for i in range(4):
            next_tail_x = tail_x + dx[i]
            next_tail_y = tail_y + dy[i]
            next_head_x = head_x + dx[i]
            next_head_y = head_y + dy[i]

            # 범위 확인
            if next_tail_x < 0 or next_tail_x >= N:
                continue
            if next_tail_y < 0 or next_tail_y >= N:
                continue
            if next_head_x < 0 or next_head_x >= N:
                continue
            if next_head_y < 0 or next_head_y >= N:
                continue

            # 벽 확인
            if board[next_tail_x][next_tail_y] == 1:
                continue
            if board[next_head_x][next_head_y] == 1:
                continue

            state = (next_tail_x,next_tail_y,next_head_x,next_head_y)

            if state not in visited:
                visited.add(state)
                queue.append((next_tail_x,next_tail_y,
                              next_head_x,next_head_y,time+1))

        # 회전
        # axis 0 : tail을 축으로 회전
        # axis 1 : head를 축으로 회전
        for axis in range(2):
            for direction in range(2):

                if axis == 0:
                    axis_x = tail_x
                    axis_y = tail_y
                    target_x = head_x
                    target_y = head_y
                else:
                    axis_x = head_x
                    axis_y = head_y
                    target_x = tail_x
                    target_y = tail_y

                # 축을 기준으로 상대좌표 구하기
                r_x = target_x - axis_x
                r_y = target_y - axis_y

                # 90도 회전
                if direction == 0:
                    next_r_x = r_y
                    next_r_y = -r_x
                else:
                    next_r_x = -r_y
                    next_r_y = r_x

                # 회전 후 움직이는 칸
                result_x = axis_x + next_r_x
                result_y = axis_y + next_r_y

                # 회전할 때 같이 비어있어야 하는 칸
                corner_x = target_x + next_r_x
                corner_y = target_y + next_r_y

                # 범위 확인
                if result_x < 0 or result_x >= N:
                    continue
                if result_y < 0 or result_y >= N:
                    continue
                if corner_x < 0 or corner_x >= N:
                    continue
                if corner_y < 0 or corner_y >= N:
                    continue

                # 벽 확인
                if board[result_x][result_y] == 1:
                    continue
                if board[corner_x][corner_y] == 1:
                    continue

                # tail이 축이면 head가 움직임
                if axis == 0:
                    next_tail_x = tail_x
                    next_tail_y = tail_y
                    next_head_x = result_x
                    next_head_y = result_y

                # head가 축이면 tail이 움직임
                else:
                    next_tail_x = result_x
                    next_tail_y = result_y
                    next_head_x = head_x
                    next_head_y = head_y

                state = (next_tail_x,next_tail_y,next_head_x,next_head_y)

                if state not in visited:
                    visited.add(state)
                    queue.append((next_tail_x,next_tail_y,
                                  next_head_x,next_head_y,time+1))