package SWEA;

import java.io.*;
import java.util.*;

class Atom {
	int number;
	int energy;
	int diretion;
	float x;
	float y;
	private final int[] dx = {0,0,-1,1};
	private final int[] dy = {1,-1,0,0};
	private final float time = 0.5f;
	public Atom(int number, int energy, int diretion, float x, float y) {
		super();
		this.number = number;
		this.energy = energy;
		this.diretion = diretion;
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Atom [energy=" + energy + ", diretion=" + diretion + ", x=" + x + ", y=" + y + "]";
	}
	
	public void move() {
		x = x + dx[diretion] * time;
		y = y + dy[diretion] * time;
	}
}

public class Solution_5648_김정원 {
	static int N, M, answer;
	static List<Atom> atoms;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			atoms = new ArrayList<>();
			answer = 0;
			for (int i = 0; i < N; i++) {				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int number = i;
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int diretion = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				atoms.add(new Atom(number,energy,diretion,x,y));
			}
			// atoms.stream().forEach(a -> System.out.println(a.toString()));
			simulation();
			System.out.println(String.format("#%d %d", test_case, answer));
		}
	}
	
	static void simulation() {
		while (!isFissionFinished()) {
			moveAllAtom();
		}
	}
	
	static void moveAllAtom() {
		Map<Float, Map<Float, int[]>> status = new HashMap<>();
		List<Integer> collisionAtoms = new ArrayList<>();
		for (Atom atom : atoms) {
			atom.move();
			float x = atom.x;
			float y = atom.y;
			int number = atom.number;
			// 관측 불가능한 영역으로 원자가 나가면 소멸로 간주
			if (x < -1005 || x > 1005 || y < -1005 || y > 1005) {
				collisionAtoms.add(number);
				continue;
			}
			Map<Float, int[]> yMap =
					status.computeIfAbsent(x, key -> new HashMap<>());
			if (yMap.containsKey(y)) {
				// 이 좌표에 이미 다른 원자가 있음
				// 충돌이 발생함
				answer += atom.energy;
				collisionAtoms.add(number);
				// 처음 도착한 원자도 소멸 처리
				if (yMap.get(y)[0] != -9999) {
					answer += yMap.get(y)[0];
					collisionAtoms.add(yMap.get(y)[1]);
					yMap.get(y)[0] = -9999;
				}
			} else {
				// 처음 도착한 원자의 에너지와 번호 저장
				yMap.put(y, new int[] {atom.energy, atom.number});
			}
		}
		for (int number : collisionAtoms) {
			for (int index = 0; index < atoms.size(); index++) {
				if (atoms.get(index).number == number) {
					atoms.remove(index);
					break;
				}
			}
		}
	}
	
	static boolean isFissionFinished() {
		return atoms.size() == 0;
	}
}




