package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
	private Node head;
	private Node tail;
	private int size;

	private final class Node {
		private E value;
		private Node next;

		private Node(E value) {
			this.value = value;
		}
	}

	public Queue() {
		this.size = 0;
	}

	@Override
	public void offer(E element) {
		Node node = new Node(element);

		if (this.head == null) {
			this.head = this.tail = node;

		} else {
			this.tail.next = node;
			this.tail = node;
		}

		size++;
	}

	@Override
	public E poll() {
		ensureNonEmpty();

		E removedElement = head.value;
		this.head = head.next;

		if (size == 1) {
			this.tail = null;
		}

		size--;

		return removedElement;
	}

	@Override
	public E peek() {
		ensureNonEmpty();

		return this.head.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private final class QueueIterator implements Iterator<E> {
		Node node = head;

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public E next() {
			E value = node.value;
			node = node.next;

			return value;
		}

	}

	private void ensureNonEmpty() {
		if (this.isEmpty()) {
			throw new IllegalStateException("You can't remove elements from empty stack!");
		}
	}
}
