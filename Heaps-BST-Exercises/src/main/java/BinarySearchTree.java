import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {
	private Node<E> root;

	public static class Node<E> {
		private E value;
		private Node<E> leftChild;
		private Node<E> rightChild;
		private int count;

		public Node(E value) {
			this.count = 1;
			this.value = value;
		}

		public Node<E> getLeft() {
			return this.leftChild;
		}

		public Node<E> getRight() {
			return this.rightChild;
		}

		public E getValue() {
			return this.value;
		}
	}

	public BinarySearchTree() {

	}

	public BinarySearchTree(Node<E> rootNode) {
		copy(rootNode);
	}

	private void copy(Node<E> rootNode) {
		while (rootNode != null) {
			insert(rootNode.value);

			copy(rootNode.leftChild);
			copy(rootNode.rightChild);

		}

	}

	public BinarySearchTree(E value) {
		this.root = new Node<>(value);
	}

	public void eachInOrder(Consumer<E> consumer) {
		nodeInOrder(this.root, consumer);

	}

	private void nodeInOrder(Node<E> node, Consumer<E> consumer) {
		if (node == null) {
			return;
		}

		nodeInOrder(node.getLeft(), consumer);
		consumer.accept(node.getValue());
		nodeInOrder(node.getRight(), consumer);

	}

	private boolean isLess(E first, E second) {
		return first.compareTo(second) < 0;
	}

	private boolean isGreater(E first, E second) {
		return first.compareTo(second) > 0;
	}

	private boolean isEqual(E first, E second) {
		return first.compareTo(second) == 0;
	}

	public Node<E> getRoot() {
		return this.root;
	}

	public void insert(E element) {
		if (this.root == null) {
			this.root = new Node<>(element);

		} else {
			insertInto(this.root, element);
		}
	}

	private void insertInto(Node<E> node, E element) {
		if (isLess(element, node.value)) {
			if (node.leftChild == null) {
				node.leftChild = new Node<>(element);

			} else {
				insertInto(node.leftChild, element);
			}
		} else if (isGreater(element, node.value)) {
			if (node.rightChild == null) {
				node.rightChild = new Node<>(element);

			} else {
				insertInto(node.rightChild, element);
			}
		}
		node.count++;
	}

	public boolean contains(E element) {
		Node<E> current = this.root;

		while (current != null) {

			if (isGreater(element, current.value)) {
				current = current.getRight();

			} else if (isLess(element, current.value)) {
				current = current.getLeft();

			} else {
				return true;
			}
		}

		return false;

	}

	public BinarySearchTree<E> search(E element) {
		Node<E> current = this.root;

		while (current != null) {
			if (isLess(element, current.value)) {
				current = current.getLeft();

			} else if (isGreater(element, current.value)) {
				current = current.getRight();

			} else {
				return new BinarySearchTree<>(current);
			}
		}

		return null;
	}

	public List<E> range(E low, E high) {
		List<E> result = new ArrayList<>();
		Deque<Node<E>> queue = new ArrayDeque<>();

		queue.offer(this.root);

		while (!queue.isEmpty()) {
			Node<E> current = queue.poll();

			if (current.getLeft() != null) {
				queue.offer(current.getLeft());
			}

			if (current.getRight() != null) {
				queue.offer(current.getRight());
			}

			if (isLess(low, current.getValue()) && isGreater(high, current.getValue())) {
				result.add(current.getValue());

			} else if (isEqual(low, current.getValue()) && isEqual(high, current.getValue())) {
				result.add(current.getValue());

			}
		}

		return result;
	}

	public void deleteMin() {
		ensureNonEmpty();

		Node<E> current = this.root;

		if (this.root.getLeft() == null) {
			this.root = this.root.getRight();
			return;
		}

		while (current.getLeft().getLeft() != null) {
			current.count--;
			current = current.getLeft();
		}

		current.count--;
		current.leftChild = current.getLeft().getRight();
	}

	public void deleteMax() {
		ensureNonEmpty();

		Node<E> current = this.root;

		if (current.getRight() == null) {
			this.root = this.root.getLeft();
			return;
		}

		while (current.rightChild.rightChild != null) {
			current.count--;
			current = current.rightChild;
		}

		current.count--;
		current.rightChild = current.getRight().getLeft();

	}

	public int count() {
		return this.root == null ? 0 : root.count;
	}

	public int rank(E element) {
		return nodeRank(this.root, element);
	}

	private int nodeRank(Node<E> node, E element) {
		if (node == null) {
			return 0;
		}
		if (isLess(element, node.getValue())) {
			return nodeRank(node.getLeft(), element);

		} else if (isEqual(element, node.getValue())) {
			return getNodeCount(node.getLeft());
		}

		return getNodeCount(node.getLeft()) + 1 + nodeRank(node.getRight(), element);
	}

	private int getNodeCount(Node<E> node) {
		return node == null ? 0 : node.count;
	}

	public E ceil(E element) {
		if (this.root == null) {
			return null;
		}

		Node<E> current = this.root;
		Node<E> nearestBigger = null;

		while (current != null) {
			if (isLess(element, current.getValue())) {
				nearestBigger = current;
				current = current.getLeft();

			} else if (isGreater(element, current.getValue())) {
				current = current.getRight();

			} else {
				Node<E> right = current.getRight();

				if (right != null && nearestBigger != null) {
					nearestBigger = isLess(right.getValue(), nearestBigger.getValue()) ? right : nearestBigger;

				} else if (nearestBigger == null) {
					nearestBigger = right;
				}
				break;
			}
		}

		return nearestBigger == null ? null : nearestBigger.getValue();
	}

	public E floor(E element) {
		if (this.root == null) {
			return null;
		}
		Node<E> current = this.root;
		Node<E> nearestSmaller = null;

		while (current != null) {
			if (isGreater(element, current.getValue())) {
				nearestSmaller = current;
				current = current.getRight();

			} else if (isLess(element, current.getValue())) {
				current = current.getLeft();

			} else {
				Node<E> left = current.getLeft();

				if (left != null && nearestSmaller != null) {
					nearestSmaller = isGreater(left.getValue(), nearestSmaller.getValue()) ? left : nearestSmaller;

				} else if (nearestSmaller == null) {
					nearestSmaller = left;
				}

				break;
			}
		}
		return nearestSmaller == null ? null : nearestSmaller.getValue();
	}

	private void ensureNonEmpty() {
		if (this.root == null) {
			throw new IllegalArgumentException();
		}
	}
}
