package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import interfaces.AbstractBinaryTree;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
	private E key;
	private BinaryTree<E> left;
	private BinaryTree<E> right;

	public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	@Override
	public E getKey() {
		return this.key;
	}

	@Override
	public AbstractBinaryTree<E> getLeft() {
		return this.left;
	}

	@Override
	public AbstractBinaryTree<E> getRight() {
		return this.right;
	}

	@Override
	public void setKey(E key) {
		this.key = key;
	}

	@Override
	public String asIndentedPreOrder(int indent) {
		StringBuilder result = new StringBuilder();
		String padding = createPadding(indent) + this.key;
		result.append(padding);

		if (getLeft() != null) {
			String preOrder = this.getLeft().asIndentedPreOrder(indent + 2);
			result.append(System.lineSeparator()).append(preOrder);
		}
		if (getRight() != null) {
			String preOrder = this.getRight().asIndentedPreOrder(indent + 2);
			result.append(System.lineSeparator()).append(preOrder);
		}

		return result.toString();
	}

	private String createPadding(int indent) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < indent; i++) {
			result.append(" ");
		}

		return result.toString();
	}

	@Override
	public List<AbstractBinaryTree<E>> preOrder() {
		List<AbstractBinaryTree<E>> result = new ArrayList<>();
		result.add(this);

		if (this.getLeft() != null) {
			result.addAll(this.getLeft().preOrder());
		}

		if (this.getRight() != null) {
			result.addAll(this.getRight().preOrder());
		}

		return result;
	}

	@Override
	public List<AbstractBinaryTree<E>> inOrder() {
		List<AbstractBinaryTree<E>> result = new ArrayList<>();

		if (this.getLeft() != null) {
			result.addAll(this.getLeft().inOrder());
		}

		result.add(this);

		if (this.getRight() != null) {
			result.addAll(this.getRight().inOrder());
		}

		return result;
	}

	@Override
	public List<AbstractBinaryTree<E>> postOrder() {
		List<AbstractBinaryTree<E>> result = new ArrayList<>();

		if (this.getLeft() != null) {
			result.addAll(this.getLeft().postOrder());
		}

		if (this.getRight() != null) {
			result.addAll(this.getRight().postOrder());
		}

		result.add(this);

		return result;
	}

	@Override
	public void forEachInOrder(Consumer<E> consumer) {
		if (this.getLeft() != null) {
			this.getLeft().forEachInOrder(consumer);
		}

		consumer.accept(this.getKey());

		if (this.getRight() != null) {
			this.getRight().forEachInOrder(consumer);
		}
	}
}
