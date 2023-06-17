package implementations;

import java.util.Iterator;

import interfaces.LinkedList;

public class DoublyLinkedList<E> implements LinkedList<E> {
	private Node head;
	private Node tail;
	private int size;

	private class Node {
		private E value;
		private Node next;
		private Node prev;

		private Node(E value) {
			this.value = value;
		}
	}

	public DoublyLinkedList() {
		this.size = 0;

	}

	@Override
	public void addFirst(E element) {
		Node newNode = new Node(element);

		if (this.head == null) {
			this.head = this.tail = newNode;

		} else {
			newNode.next = this.head;
			this.head.prev = newNode;
			this.head = newNode;

		}
		this.size++;
	}

	@Override
	public void addLast(E element) {
		if (size == 0) {
			addFirst(element);

		} else {
			Node newNode = new Node(element);
			this.tail.next = newNode;
			newNode.prev = this.tail;
			this.tail = newNode;

			this.size++;
		}
	}

	@Override
	public E removeFirst() {
		ensureNonEmpty("remove");

		E value = head.value;

		if (size == 1) {
			this.head = this.tail = null;

		} else {
			this.head = head.next;
			this.head.prev = null;

		}
		this.size--;

		return value;
	}

	@Override
	public E removeLast() {
		ensureNonEmpty("remove");
		E value = this.tail.value;

		if (size == 1) {
			this.removeFirst();

		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;

			this.size--;
		}

		return value;
	}

	@Override
	public E getFirst() {
		ensureNonEmpty("get");

		return this.head.value;
	}

	@Override
	public E getLast() {
		ensureNonEmpty("get");

		return this.tail.value;
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
		return null;
	}

	private void ensureNonEmpty(String command) {
		if (this.isEmpty()) {
			throw new IllegalStateException(String.format("You can't %s elements from empty LinkedList!", command));
		}
	}
}
