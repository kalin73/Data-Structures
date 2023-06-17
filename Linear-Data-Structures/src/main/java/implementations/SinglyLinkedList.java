package implementations;

import java.util.Iterator;

import interfaces.LinkedList;

public class SinglyLinkedList<E> implements LinkedList<E> {
	private Node first;
	private int size;

	private class Node {
		private E value;
		private Node next;

		private Node(E value) {
			this.value = value;
		}
	}

	public SinglyLinkedList() {
		this.size = 0;
		this.first = new Node(null);
	}

	@Override
	public void addFirst(E element) {
		Node newNode = new Node(element);

		newNode.next = first;
		this.first = newNode;

		size++;

	}

	@Override
	public void addLast(E element) {
		Node newNode = new Node(element);

		Node currNode = first;

		while (currNode.next != null) {
			currNode = currNode.next;
		}
		currNode.next = newNode;
	}

	@Override
	public E removeFirst() {
		ensureNonEmpty("remove");

		E value = first.value;
		this.first = first.next;

		size--;

		return value;
	}

	@Override
	public E removeLast() {
		ensureNonEmpty("remove");

		Node currNode = first;

		while (currNode.next != null) {
			currNode = currNode.next;
		}
		E value = currNode.value;
		
		currNode = null;

		size--;
		
		return value;
	}

	@Override
	public E getFirst() {
		ensureNonEmpty("get");

		return this.first.value;
	}

	@Override
	public E getLast() {
		ensureNonEmpty("get");

		Node currNode = first;

		while (currNode.next != null) {
			currNode = currNode.next;
		}

		return currNode.value;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
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
