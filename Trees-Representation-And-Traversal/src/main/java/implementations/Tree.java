package implementations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import interfaces.AbstractTree;

public class Tree<E> implements AbstractTree<E> {
	private E value;
	private Tree<E> parent;
	private List<Tree<E>> children;

	public Tree(E value) {
		this.value = value;
		this.parent = null;
		this.children = new ArrayList<>();
	}

	@SafeVarargs
	public Tree(E value, Tree<E>... children) {
		this(value);

		for (Tree<E> child : children) {
			child.parent = this;
			this.children.add(child);
		}
	}

	@Override
	public List<E> orderBfs() {
		final List<E> result = new ArrayList<>();
		final ArrayDeque<Tree<E>> childrenQueue = new ArrayDeque<>();

		if (this.value == null) {
			return result;
		}

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
		final List<E> result = new ArrayList<>();

		this.doDfs(this, result);
//		ArrayDeque<Tree<E>> stack = new ArrayDeque<>();
//		stack.push(this);
//
//		while (!stack.isEmpty()) {
//			Tree<E> current = stack.pop();
//
//			for (Tree<E> child : current.children) {
//				stack.push(child);
//			}
//			
//			result.add(current.value);
//		}
//		Collections.reverse(result);

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
		Tree<E> search = findBfs(parentKey);

		if (search == null) {
			throw new IllegalArgumentException();
		}

		search.children.add(child);
		child.parent = search;

	}

	@Override
	public void removeNode(E nodeKey) {
		Tree<E> toRemove = this.findBfs(nodeKey);

		if (toRemove == null) {
			throw new IllegalArgumentException();
		}

		if (toRemove.parent == null) {
			toRemove.children.clear();
			toRemove.value = null;

		} else {
			Tree<E> parent = toRemove.parent;
			parent.children.remove(toRemove);

		}

	}

	@Override
	public void swap(E firstKey, E secondKey) {
		Tree<E> firstNode = findBfs(firstKey);
		Tree<E> secondNode = findBfs(secondKey);

		if (firstNode == null || secondNode == null) {
			throw new IllegalArgumentException();
		}

		if (firstNode.parent == null) {
			swapRoot(secondNode);
			return;

		} else if (secondNode.parent == null) {
			swapRoot(firstNode);
			return;

		}
		Tree<E> firstParent = firstNode.parent;
		Tree<E> secondParent = secondNode.parent;

		int firstNodePosition = findIndex(firstNode.parent.children, firstNode);
		int secondNodePosition = findIndex(secondNode.parent.children, secondNode);

		firstNode.parent = secondParent;
		secondNode.parent = firstParent;

		firstParent.children.set(firstNodePosition, secondNode);

		secondParent.children.set(secondNodePosition, firstNode);

	}

	private Tree<E> findBfs(E parentKey) {
		ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

		queue.offer(this);

		while (!queue.isEmpty()) {
			Tree<E> current = queue.pop();

			if (current.value.equals(parentKey)) {
				return current;
			}

			for (Tree<E> child : current.children) {
				queue.offer(child);
			}
		}

		return null;
	}

	private int findIndex(List<Tree<E>> children, Tree<E> child) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	private void swapRoot(Tree<E> node) {
		this.value = node.value;
		this.parent = null;
		this.children = node.children;
		node.parent = null;
	}
}
