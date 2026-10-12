package com.ssafy.pro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_외벽점검_김정원 {
    static List<Integer> weak;
    static List<Integer> dist;
    static int[] peopleBatch;
    static boolean[] selected;
    static int totalPeople;
    static int peopleCnt;
    static int n;

public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    weak = new ArrayList<>();
    dist = new ArrayList<>();

    n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    while (st.hasMoreTokens()) {
        weak.add(Integer.parseInt(st.nextToken()));
    }

    st = new StringTokenizer(br.readLine());

    while (st.hasMoreTokens()) {
        dist.add(Integer.parseInt(st.nextToken()));
    }

    int answer = solution(
            n,
            weak.stream().mapToInt(Integer::intValue).toArray(),
            dist.stream().mapToInt(Integer::intValue).toArray()
    );

    System.out.println(answer);
}

static int solution(int wallLength, int[] weakInput, int[] distInput) {
    n = wallLength;
    totalPeople = distInput.length;

    weak = new ArrayList<>();
    dist = new ArrayList<>();

    for (int position : weakInput) {
        weak.add(position);
    }

    for (int distance : distInput) {
        dist.add(distance);
    }

    // 사람 수를 늘려가면서 취약 지점을 점검할 수 있는 최소 인원 수 까지 탐색하기
    for (int count = 1; count <= totalPeople; count++) {
        peopleCnt = count;
        peopleBatch = new int[count];
        selected = new boolean[totalPeople];

        // 현재 인원으로 점검이 가능하다면 즉시 종료
        if (weakCheck(0)) {
            return count;
        }
    }

    // 모든 근무 인원을 배치해도 점검이 불가능하다면 -1 반환
    return -1;
}

static boolean weakCheck(int count) {
    if (count == peopleCnt) {
        // 현재 취약 외벽에 배치로 점검이 가능하다면 리턴
        return weakBatch();
    }

    // 근무 인원의 배치 순서에서 나올 수 있는 모든 경우의수를 확인한다
    for (int people = 0; people < totalPeople; people++) {
        // 이미 근무표에 들어간 인원이라면 넘어간다
        if (selected[people]) continue;

        selected[people] = true;
        peopleBatch[count] = people;

        boolean possible = weakCheck(count + 1);

        // 다른 근무표를 만들 수 있도록 선택을 해제한다
        selected[people] = false;

        // 점검이 가능한 근무표가 나왔다면 리턴하여 종료
        if (possible) {
            return true;
        }
    }

    return false;
}

static boolean weakBatch() {
    int weakCount = weak.size();

    // 근무표가 나온 근무 인원들을 배치해야한다
    // 먼저 시작 근무 장소를 정한다
    for (int start = 0; start < weakCount; start++) {
        // 아직 점검이 필요한 취약지점의 인덱스
        int index = start;

        // 점검이 완료된 취약지점의 갯수
        int checkedCount = 0;

        // 시작점에서 부터 근무 인원의 근무할 수 있는 거리를 채워 나간다
        for (int people = 0; people < peopleBatch.length; people++) {
            // 현재 근무 인원이 이동할 수 있는 거리
            int curPower = dist.get(peopleBatch[people]);

            // 현재 근무 인원이 근무를 시작하는 실제 위치
            int startPosition = weak.get(index);

            while (checkedCount < weakCount) {
                // 시작 위치에서 현재 취약지점까지 시계방향으로 이동하는 거리
                int distance =
                        (weak.get(index) - startPosition + n) % n;

                // 현재 근무 인원이 점검할 수 없는 거리라면 다음 인원으로 넘어간다
                if (distance > curPower) {
                    break;
                }

                // 현재 취약지점을 점검 완료하고 다음 취약지점으로 넘어간다
                checkedCount++;

                // 마지막 취약지점 다음은 처음 취약지점으로 돌아간다
                index = (index + 1) % weakCount;
            }

            // 모든 취약지점이 점검 완료되었다면 리턴하여 종료
            if (checkedCount == weakCount) {
                return true;
            }

            // 다음 근무 인원은 아직 점검하지 않은 취약지점에서 근무를 시작한다
        }
    }

    return false;
}
}

