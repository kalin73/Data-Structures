package solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
		Map<Integer, Pair<Integer, Integer>> offsetToValueLevel = new TreeMap<>();

		traversTree(this, 0, 1, offsetToValueLevel);

		return offsetToValueLevel
				.values()
				.stream()
				.map(Pair::getKey)
				.collect(Collectors.toList());
	}

	private void traversTree(BinaryTree binaryTree, int offset, int level,
			Map<Integer, Pair<Integer, Integer>> offsetToValueLevel) {
		if (binaryTree == null) {
			return;
		}

		Pair<Integer, Integer> currentValueLevel = offsetToValueLevel.get(offset);

		if (currentValueLevel == null || level < currentValueLevel.getValue()) {
			offsetToValueLevel.put(offset, new Pair<>(binaryTree.key, level));

		}

		traversTree(binaryTree.left, offset - 1, level + 1, offsetToValueLevel);
		traversTree(binaryTree.right, offset + 1, level + 1, offsetToValueLevel);
	}
}
