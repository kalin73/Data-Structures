package implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
		return doDfs(this);
	}

	private Tree<E> doDfs(Tree<E> tree) {
		Tree<E> node = tree;

		while (!node.children.isEmpty()) {
			node = node.children.get(0);
		}

		return node;
	}

	@Override
	public List<E> getLongestPath() {
		return null;
	}

	@Override
	public List<List<E>> pathsWithGivenSum(int sum) {
		return null;
	}

	@Override
	public List<Tree<E>> subTreesWithGivenSum(int sum) {
		return null;
	}
}
