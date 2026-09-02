import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static class Node {
		String value;
		int left;
		int right;

		Node(String value) {
			this.value = value;
		}

	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			int T;
			T = Integer.parseInt(br.readLine());
			Node[] tree = new Node[T + 1];

			int result = 0;
			for (int i = 0; i < T; i++) {
				st = new StringTokenizer(br.readLine());

				int nodeIndex = Integer.parseInt(st.nextToken());
				String value = st.nextToken();

				Node node = new Node(value);

				if (st.countTokens() == 2) {
					node.left = Integer.parseInt(st.nextToken());
					node.right = Integer.parseInt(st.nextToken());
				}

				tree[nodeIndex] = node;

			}
			boolean valid = isValid(tree, 1);
			if (valid) {
				result = 1;
			} else {
				result = 0;
			}
			System.out.println("#" + test_case + " " + result);
		}
	}

	static boolean isValid(Node[] tree, int nodeIndex) {
		Node node = tree[nodeIndex];

		boolean hasLeft = node.left != 0;
		boolean hasRight = node.right != 0;
		boolean isOperator = isOperator(node.value);

		// 리프 노드
		if (!hasLeft && !hasRight) {
			return !isOperator;
		}

		// 자식이 하나만 있는 경우
		if (!hasLeft || !hasRight) {
			return false;
		}

		// 자식이 있는데 숫자인 경우
		if (!isOperator) {
			return false;
		}

		return isValid(tree, node.left) && isValid(tree, node.right);
	}

	static boolean isOperator(String value) {
		return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
	}
}