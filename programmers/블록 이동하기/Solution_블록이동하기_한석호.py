from collections import deque 

def solution(board):
    answer = 0
    n = len(board)
    m = len(board[0])

    # visited는 [x][y][d]
    visited = []
    for i in range(n):
        matrix = []
        for j in range(m):
            row = [float('inf')] * 2 # 상태는 총 2가지
            matrix.append(row)

        visited.append(matrix)

    visited[0][0][0] = 0

    queue = deque()
    queue.append((0, 0, 0))

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    def rotate(x, y, d, queue):
        # 눕혀져 있는데 세워야 함 (d = 0 -> d = 1)
        if d == 0:
            # (nx, ny 오프셋, c_nx, c_ny 오프셋)
            # 회전 가능한 4가지 케이스 (좌/우 축 x 위/아래 회전)
            rot_offsets = [
                (-1, 0, -1, 1),  # 좌측 축, 위로 회전
                (0, 0, 1, 1),    # 좌측 축, 아래로 회전
                (-1, 1, -1, 0),  # 우측 축, 위로 회전
                (0, 1, 1, 0)     # 우측 축, 아래로 회전
            ]
            for r_x, r_y, c_x, c_y in rot_offsets:
                nx, ny = x + r_x, y + r_y
                c_nx, c_ny = x + c_x, y + c_y

                # 1. 새로 놓일 세로 로봇 범위 및 벽 검사
                if 0 <= nx < n and 0 <= ny < m and 0 <= nx + 1 < n:
                    # 2. 대각선 좌표 범위 및 벽 검사
                    if 0 <= c_nx < n and 0 <= c_ny < m:
                        if board[nx][ny] != 1 and board[nx+1][ny] != 1 and board[c_nx][c_ny] != 1:
                            if visited[nx][ny][1] == float('inf'):
                                visited[nx][ny][1] = visited[x][y][0] + 1
                                queue.append((nx, ny, 1))

        # 세워져있는거 눕혀야 함 (d = 1 -> d = 0)
        else:
            # 회전 가능한 4가지 케이스 (상/하 축 x 좌/우 회전)
            rot_offsets = [
                (0, -1, 1, -1),  # 상단 축, 좌로 회전
                (0, 0, 1, 1),    # 상단 축, 우로 회전
                (1, -1, 0, -1),  # 하단 축, 좌로 회전
                (1, 0, 0, 1)     # 하단 축, 우로 회전
            ]
            for r_x, r_y, c_x, c_y in rot_offsets:
                nx, ny = x + r_x, y + r_y
                c_nx, c_ny = x + c_x, y + c_y

                # 1. 새로 놓일 가로 로봇 범위 및 벽 검사
                if 0 <= nx < n and 0 <= ny < m and 0 <= ny + 1 < m:
                    # 2. 대각선 좌표 범위 및 벽 검사
                    if 0 <= c_nx < n and 0 <= c_ny < m:
                        if board[nx][ny] != 1 and board[nx][ny+1] != 1 and board[c_nx][c_ny] != 1:
                            if visited[nx][ny][0] == float('inf'):
                                visited[nx][ny][0] = visited[x][y][1] + 1
                                queue.append((nx, ny, 0))


    def move(x, y, d, queue):
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 본인 먼저 벽 뚫는지 확인하고, 만약 도착 시간이 이전에 왔던 것 보다 더 적으면
            if 0 <= nx < n and 0 <= ny < m and not board[nx][ny] == 1 and visited[nx][ny][d] == float('inf'):
                # 만약 가로 였을 때
                if d == 0:
                    # 항상 좌표는 본인 기준 오른쪽 (0, 1)에 잇음
                    if 0 <= nx < n and 0 <= ny + 1 < m and board[nx][ny+1] != 1:
                        # 큐에 넣기
                        visited[nx][ny][0] = visited[x][y][0] + 1
                        queue.append((nx, ny, 0))

                # 만약 d가 세로 였을 때, 그리고 내 밑에 잇는 로봇이 벽에 안걸렸을 때
                else:
                    if 0 <= nx + 1 < n and 0 <= ny < m and board[nx+1][ny] != 1:
                        # 큐에 넣기
                        visited[nx][ny][1] = visited[x][y][1] + 1
                        queue.append((nx, ny, 1))


    while queue:
        x, y, d = queue.popleft()

        if d == 0 and x == n-1 and y == m-2:
            return visited[x][y][0]

        elif d == 1 and x == n-2 and y == m-1:
            return visited[x][y][1]

        rotate(x, y, d, queue)
        move(x, y, d, queue)