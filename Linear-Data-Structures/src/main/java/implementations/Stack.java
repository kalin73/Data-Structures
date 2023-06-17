package implementations;

import java.util.Iterator;

import interfaces.AbstractStack;

public class Stack<E> implements AbstractStack<E> {
	private Node topElement;
	private int size;

	public Stack() {
		this.topElement = null;
		this.size = 0;
	}

	private final class Node {
		private E value;
		private Node next;

		private Node(E value) {
			this.value = value;
		}
	}

	@Override
	public void push(E element) {
		Node node = new Node(element);

		node.next = this.topElement;
		this.topElement = node;

		size++;
	}

	@Override
	public E pop() {
		ensureNonEmpty();

		E removedElement = topElement.value;
		this.topElement = this.topElement.next;
		size--;

		return removedElement;
	}

	@Override
	public E peek() {
		ensureNonEmpty();

		return this.topElement.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}

	private final class StackIterator implements Iterator<E> {
		private Node top = topElement;

		@Override
		public boolean hasNext() {
			return top != null;
		}

		@Override
		public E next() {
			E val = top.value;
			this.top = top.next;

			return val;
		}

	}

	private void ensureNonEmpty() {
		if (this.isEmpty()) {
			throw new IllegalStateException("You can't remove elements from empty stack!");
		}
	}
}
