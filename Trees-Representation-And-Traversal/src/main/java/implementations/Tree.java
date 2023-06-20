package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
	private E value;
	private Tree<E> parent;
	private List<Tree<E>> children;

	public Tree(E value) {
		this.value = value;
		this.parent = null;
		this.children = new ArrayList<>();
	}

	public Tree(E value, Tree<E>... children) {
		this(value);
		Collections.addAll(this.children, children);

	}

	@Override
	public List<E> orderBfs() {
		final List<E> result = new ArrayList<>();
		final ArrayDeque<Tree<E>> childrenQueue = new ArrayDeque<>();

		childrenQueue.offer(this);

		while (!childrenQueue.isEmpty()) {
			Tree<E> current = childrenQueue.poll();

			result.add(current.value);

			current.children.forEach(child -> childrenQueue.offer(child));

		}

		return result;
	}

	@Override
	public List<E> orderDfs() {
		final List<E> result = new ArrayList();

		this.doDfs(this, result);

		return result;
	}

	private void doDfs(Tree<E> node, List<E> result) {
		for (Tree<E> child : node.children) {
			this.doDfs(child, result);

		}
		result.add(node.value);
	}

	@Override
	public void addChild(E parentKey, Tree<E> child) {

	}

	@Override
	public void removeNode(E nodeKey) {

	}

	@Override
	public void swap(E firstKey, E secondKey) {

	}
}
