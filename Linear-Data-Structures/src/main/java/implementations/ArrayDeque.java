package implementations;

import java.util.Iterator;

import interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {
	private static final int INITIAL_CAPACITY = 7;
	private int size;
	private int head;
	private int tail;
	private Object[] elements;

	public ArrayDeque() {
		this.size = 0;
		this.head = this.tail = INITIAL_CAPACITY / 2;
		this.elements = new Object[INITIAL_CAPACITY];

	}

	@Override
	public void add(E element) {
		this.addLast(element);
	}

	@Override
	public void offer(E element) {
		this.addLast(element);
	}

	@Override
	public void addFirst(E element) {
		if (size == 0) {
			this.addLast(element);

		} else {
			if (this.head == 0) {
				this.grow();
			}
			this.elements[--this.head] = element;
			this.size++;
		}
	}

	@Override
	public void addLast(E element) {
		if (this.size == 0) {
			this.elements[tail] = element;

		} else {
			if (this.tail == this.elements.length - 1) {
				this.grow();
			}

			this.elements[++this.tail] = element;

		}

		this.size++;
	}

	@Override
	public void push(E element) {
		this.addFirst(element);
	}

	@Override
	public void insert(int index, E element) {
		int realIndex = this.head + index;
		this.ensureIndex(realIndex);

		if (realIndex - this.head < this.tail - realIndex) {

			if (realIndex == this.head) {
				this.addFirst(element);
				return;
			}
			shiftLeft(realIndex - 1, element);

		} else {
			shiftRight(realIndex, element);
		}

	}

	@Override
	public void set(int index, E element) {
		int realIndex = this.head + index;
		this.ensureIndex(realIndex);

		this.elements[realIndex] = element;

	}

	@Override
	public E peek() {
		if (this.ensureNonEmpty()) {
			return null;
		}

		return getAtIndex(this.head);
	}

	@Override
	public E poll() {
		if (this.ensureNonEmpty()) {
			return null;
		}

		return this.removeFirst();
	}

	@Override
	public E pop() {
		if (this.ensureNonEmpty()) {
			return null;
		}

		return this.removeFirst();
	}

	@Override
	public E get(int index) {
		int realIndex = this.head + index;
		ensureIndex(realIndex);

		return this.getAtIndex(realIndex);
	}

	@Override
	public E get(Object object) {
		if (isEmpty() || object == null) {
			return null;
		}

		for (int i = head; i <= tail; i++) {
			if (elements[i].equals(object)) {
				return getAtIndex(i);
			}
		}
		return null;
	}

	@Override
	public E remove(int index) {
		int realIndex = this.head + index;
		ensureIndex(realIndex);

		E element = this.getAtIndex(realIndex);
		this.elements[realIndex] = null;

		for (int j = realIndex; j < this.tail; j++) {
			this.elements[j] = this.elements[j + 1];
		}

		this.removeLast();

		return element;
	}

	@Override
	public E remove(Object object) {
		if (isEmpty() || object == null) {
			return null;
		}

		for (int i = head; i <= tail; i++) {
			if (elements[i].equals(object)) {
				E element = this.getAtIndex(i);
				this.elements[i] = null;

				for (int j = i; j < this.tail; j++) {
					this.elements[j] = this.elements[j + 1];
				}

				this.removeLast();

				return element;
			}
		}
		return null;
	}

	@Override
	public E removeFirst() {
		if (ensureNonEmpty()) {
			return null;
		}

		E element = this.getAtIndex(head);
		this.elements[this.head++] = null;
		this.size--;

		return element;
	}

	@Override
	public E removeLast() {
		if (ensureNonEmpty()) {
			return null;
		}
		E element = this.getAtIndex(this.tail);
		elements[this.tail--] = null;
		size--;

		return element;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int capacity() {
		return this.elements.length;
	}

	@Override
	public void trimToSize() {
		Object[] newElements = new Object[this.size];
		int index = 0;

		for (int i = this.head; i <= this.tail; i++) {
			newElements[index++] = this.elements[i];
		}

		this.elements = newElements;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = head;

			@Override
			public boolean hasNext() {
				return index <= tail && size > 0;
			}

			@Override
			public E next() {
				return getAtIndex(index++);
			}

		};
	}

	private void grow() {
		int newCapacity = this.elements.length * 2 + 1;

		Object[] newElements = new Object[newCapacity];

		int middle = newCapacity / 2;

		int begin = middle - this.size / 2;

		int index = this.head;

		for (int i = begin; index <= this.tail; i++) {
			newElements[i] = this.elements[index++];

		}

		this.head = begin;
		this.tail = head + size - 1;

		this.elements = newElements;
	}

	@SuppressWarnings("unchecked")
	private E getAtIndex(int index) {
		return (E) this.elements[index];
	}

	private boolean ensureNonEmpty() {
		if (this.isEmpty()) {
			return true;
		}
		return false;
	}

	private void ensureIndex(int realIndex) {
		if (realIndex < this.head || realIndex > this.tail) {
			throw new IndexOutOfBoundsException("Index out of bounds for index: " + (realIndex - this.head));
		}

	}

	private void shiftRight(int index, E element) {
		E lastElement = this.getAtIndex(this.tail);

		for (int i = this.tail; i > index; i--) {
			this.elements[i] = this.elements[i - 1];
		}

		this.elements[index] = element;
		this.addLast(lastElement);
	}

	private void shiftLeft(int index, E element) {
		E firstElement = this.getAtIndex(this.head);

		for (int i = this.head; i < index; i++) {
			this.elements[i] = this.elements[i + 1];
		}
		
		this.elements[index] = element;
		this.addFirst(firstElement);
	}
}
