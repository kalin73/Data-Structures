package implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.AbstractTree;

public class Tree<E> implements AbstractTree<E> {
	private Tree<E> parent;
	private List<Tree<E>> children;
	private E value;

	public Tree(E value) {
		this.value = value;
		this.children = new ArrayList<>();
	}

	@Override
	public void setParent(Tree<E> parent) {
		this.parent = parent;
	}

	@Override
	public void addChild(Tree<E> child) {
		this.children.add(child);
	}

	@Override
	public Tree<E> getParent() {
		return this.parent;
	}

	@Override
	public E getKey() {
		return this.value;
	}

	@Override
	public String getAsString() {
		StringBuilder sb = new StringBuilder();

		traverseTreeWithRecurrance(sb, 0, this);

		return sb.toString().trim();
	}

	private void traverseTreeWithRecurrance(StringBuilder builder, int indent, Tree<E> tree) {
		builder.append(this.getPadding(indent)).append(tree.getKey()).append(System.lineSeparator());

		for (Tree<E> child : tree.children) {
			traverseTreeWithRecurrance(builder, indent + 2, child);
		}
	}

	private String getPadding(int size) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < size; i++) {
			builder.append(" ");
		}

		return builder.toString();
	}

	@Override
	public List<E> getLeafKeys() {
		List<E> leafKeys = getAllLeafesWithBfs();

		return leafKeys;
	}

	private List<E> getAllLeafesWithBfs() {
		List<E> leafKeys = new ArrayList<>();

		ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
		queue.offer(this);

		while (!queue.isEmpty()) {
			Tree<E> node = queue.poll();

			for (Tree<E> child : node.children) {
				queue.offer(child);
			}

			if (node.children.isEmpty()) {
				leafKeys.add(node.value);
			}

		}

		return leafKeys;
	}

	@Override
	public List<E> getMiddleKeys() {
		List<Tree<E>> collection = new ArrayList<>();
		List<E> result = new ArrayList<>();

		traverseTreeWithRecurrance(collection, this);

		result = collection.stream().filter(x -> x.parent != null && !x.children.isEmpty()).map(Tree::getKey)
				.collect(Collectors.toList());

		return result;
	}

	private void traverseTreeWithRecurrance(List<Tree<E>> collection, Tree<E> tree) {
		collection.add(tree);
		for (Tree<E> child : tree.children) {
			traverseTreeWithRecurrance(collection, child);
		}
	}

	@Override
	public Tree<E> getDeepestLeftmostNode() {
		Tree<E> deepestNode = null;
		List<Tree<E>> leafes = getAllLeafes();
		int maxPath = 0;

		for (Tree<E> leafe : leafes) {
			int currentPath = getStepsFromLeafeToRoot(leafe);

			if (maxPath < currentPath) {
				deepestNode = leafe;
				maxPath = currentPath;
			}

		}

		return deepestNode;
	}

	private int getStepsFromLeafeToRoot(Tree<E> leafe) {
		int path = 0;

		while (leafe.parent != null) {
			leafe = leafe.parent;
			path++;
		}

		return path;
	}

	private List<Tree<E>> getAllLeafes() {
		List<Tree<E>> leafes = new ArrayList<>();

		ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

		queue.offer(this);

		while (!queue.isEmpty()) {
			Tree<E> currNode = queue.poll();

			if (currNode.children.isEmpty()) {
				leafes.add(currNode);
			}

			for (Tree<E> child : currNode.children) {
				queue.offer(child);
			}

		}

		return leafes;
	}

	@Override
	public List<E> getLongestPath() {
		List<E> longest = new ArrayList<>();
		int[] currMax = { 0 };

		findLongestPathWithDFS(longest, currMax, 0, this);

		Collections.reverse(longest);

		return longest;
	}

	private void findLongestPathWithDFS(List<E> longest, int[] max, int currMax, Tree<E> node) {
		if (max[0] < currMax && node.children.isEmpty()) {
			max[0] = currMax;
			longest.clear();

			while (node != null) {
				longest.add(node.value);
				node = node.parent;
			}

			return;
		}

		for (Tree<E> child : node.children) {
			findLongestPathWithDFS(longest, max, currMax + 1, child);
		}

	}

	@Override
	public List<List<E>> pathsWithGivenSum(int sum) {
		List<List<E>> paths = new ArrayList<>();
		int[] currSum = { 0 };

		findPathsWithGivenSumDFS(paths, currSum, sum, this);

		for (List<E> list : paths) {
			Collections.reverse(list);
		}

		return paths;
	}

	private void findPathsWithGivenSumDFS(List<List<E>> paths, int[] currSum, int sum, Tree<E> node) {
		int value = (int) node.value;
		currSum[0] += value;

		if (currSum[0] == sum) {
			List<E> path = new ArrayList<>();
			currSum[0] -= value;

			while (node != null) {
				path.add(node.value);
				node = node.parent;
			}

			paths.add(path);

			return;
		}

		for (Tree<E> child : node.children) {
			findPathsWithGivenSumDFS(paths, currSum, sum, child);
		}
		currSum[0] -= value;
	}

	@Override
	public List<Tree<E>> subTreesWithGivenSum(int sum) {
		return null;
	}
}
