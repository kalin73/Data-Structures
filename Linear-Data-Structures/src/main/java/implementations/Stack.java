package implementations;

import java.util.Iterator;

import interfaces.AbstractStack;

public class Stack<E> implements AbstractStack<E> {
	private Node topElement;
	private int size;

	public Stack() {
		this.size = 0;
	}

	private final class Node {
		private E value;
		private Node prev;

		private Node(E value) {
			this.value = value;
			this.prev = null;
		}
	}

	@Override
	public void push(E element) {
		Node node = new Node(element);
		
		if (size == 0) {
			this.topElement = node;

		} else {

			node.prev = this.topElement;
			this.topElement = node;

		}
		size++;
	}

	@Override
	public E pop() {
		if (this.isEmpty()) {
			throw new IllegalStateException("You can't remove elements from empty stack!");
		}

		E removedElement = topElement.value;
		this.topElement = this.topElement.prev;
		size--;

		return removedElement;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new IllegalStateException("You can't peek empty stack!");
		}

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
		Node top = topElement;

		@Override
		public boolean hasNext() {
			return top.prev != null;
		}

		@Override
		public E next() {
			E val = top.value;
			top = top.prev;
			
			return val;
		}

	}
}
