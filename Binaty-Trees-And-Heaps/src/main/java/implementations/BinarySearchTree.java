package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
	private Node<E> root;

	public BinarySearchTree() {

	}

	public BinarySearchTree(Node<E> root) {
		this.copy(root);
	}

	private void copy(Node<E> node) {
		if (node != null) {
			this.insert(node.value);
			this.copy(node.leftChild);
			this.copy(node.rightChild);
		}
	}

	@Override
	public void insert(E element) {
		Node<E> newNode = new Node<>(element);

		if (this.getRoot() == null) {
			this.root = newNode;

		} else {
			Node<E> current = this.root;
			Node<E> prev = null;

			while (current != null) {
				prev = current;

				if (isLess(element, current.value)) {
					current = current.leftChild;

				} else if (isGreated(element, current.value)) {
					current = current.rightChild;
				}
			}
			current = newNode;

			if (isLess(element, prev.value)) {
				prev.leftChild = current;

			} else if (isGreated(element, prev.value)) {
				prev.rightChild = current;
			}
		}

	}

	private boolean isLess(E first, E second) {
		return first.compareTo(second) < 0;
	}

	private boolean isGreated(E first, E second) {
		return first.compareTo(second) > 0;
	}

	@Override
	public boolean contains(E element) {
		Node<E> current = this.getRoot();

		while (current != null) {
			if (isLess(element, current.value)) {
				current = current.leftChild;

			} else if (isGreated(element, current.value)) {
				current = current.rightChild;

			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public AbstractBinarySearchTree<E> search(E element) {
		Node<E> current = this.getRoot();

		while (current != null) {
			if (isLess(element, current.value)) {
				current = current.leftChild;

			} else if (isGreated(element, current.value)) {
				current = current.rightChild;

			} else {
				return new BinarySearchTree<>(current);
			}
		}
		return null;
	}

	@Override
	public Node<E> getRoot() {
		return this.root;
	}

	@Override
	public Node<E> getLeft() {
		return this.root.leftChild;
	}

	@Override
	public Node<E> getRight() {
		return this.root.rightChild;
	}

	@Override
	public E getValue() {
		return this.root.value;
	}
}
