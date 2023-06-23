package implementations;

import java.util.ArrayList;
import java.util.List;

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

		traverseTreeWithRecurance(sb, 0, this);

		return sb.toString().trim();
	}

	private void traverseTreeWithRecurance(StringBuilder builder, int indent, Tree<E> tree) {
		builder.append(this.getPadding(indent)).append(tree.getKey()).append(System.lineSeparator());

		for (Tree<E> child : tree.children) {
			traverseTreeWithRecurance(builder, indent + 2, child);
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
		return null;
	}

	@Override
	public List<E> getMiddleKeys() {
		return null;
	}

	@Override
	public Tree<E> getDeepestLeftmostNode() {
		return null;
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
