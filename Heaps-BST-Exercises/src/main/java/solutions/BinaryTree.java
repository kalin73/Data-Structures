package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	private int key;
	private BinaryTree left;
	private BinaryTree right;

	public BinaryTree(int key, BinaryTree first, BinaryTree second) {
		this.key = key;
		this.left = first;
		this.right = second;
	}

	public Integer findLowestCommonAncestor(int first, int second) {
		List<Integer> firstPath = findPath(first);
		List<Integer> secondPath = findPath(second);

		int smallerSize = Math.min(firstPath.size(), secondPath.size());

		int i = 0;
		for (; i < smallerSize; i++) {
			if (!firstPath.get(i).equals(secondPath.get(i))) {
				break;
			}
		}

		return firstPath.get(i - 1);
	}

	private List<Integer> findPath(int element) {
		List<Integer> result = new ArrayList<>();

		findNodePath(this, element, result);

		return result;
	}

	private void findNodePath(BinaryTree binaryTree, int element, List<Integer> result) {
		if (binaryTree == null) {
			return;
		}

		if (binaryTree.key == element) {
			return;
		}

		result.add(binaryTree.key);

		findNodePath(binaryTree.left, element, result);
		findNodePath(binaryTree.right, element, result);

	}

	public List<Integer> topView() {
		return null;
	}
}
